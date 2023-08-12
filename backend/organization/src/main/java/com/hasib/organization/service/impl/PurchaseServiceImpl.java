package com.hasib.organization.service.impl;

import com.hasib.organization.dto.*;
import com.hasib.organization.dto.bank.BankTransactionRequestDto;
import com.hasib.organization.dto.bank.BankTransactionResponseDto;
import com.hasib.organization.exception.*;
import com.hasib.organization.mapper.PurchaseMapper;
import com.hasib.organization.model.Product;
import com.hasib.organization.model.Purchase;
import com.hasib.organization.model.PurchaseItem;
import com.hasib.organization.model.UserEntity;
import com.hasib.organization.repository.ProductRepository;
import com.hasib.organization.repository.PurchaseItemRepository;
import com.hasib.organization.repository.PurchaseRepository;
import com.hasib.organization.repository.UserRepository;
import com.hasib.organization.service.PurchaseService;
import com.hasib.organization.util.ApplicationConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class PurchaseServiceImpl implements PurchaseService {

    private final PurchaseRepository purchaseRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;
    private final PurchaseItemRepository purchaseItemRepository;
    private final WebClient bankClient;

    @Autowired
    public PurchaseServiceImpl(PurchaseRepository purchaseRepository, ProductRepository productRepository, UserRepository userRepository, PurchaseItemRepository purchaseItemRepository, WebClient bankClient) {
        this.purchaseRepository = purchaseRepository;
        this.productRepository = productRepository;
        this.userRepository = userRepository;
        this.purchaseItemRepository = purchaseItemRepository;
        this.bankClient = bankClient;
    }

    @Override
    public PurchaseResponseDto createPurchase(PurchaseDto purchaseDto) {
        UserEntity buyer = userRepository.findById(purchaseDto.getBuyerId()).orElseThrow(() -> new UserNotFoundException("User not found!"));

        Set<PurchaseItem> purchaseItems = purchaseDto.getPurchases().stream()
                .map(purchaseItemDto -> {
                    Product product = productRepository.findById(purchaseItemDto.getProductId()).orElseThrow(() -> new ProductNotFoundException("Product not found!"));
                    return PurchaseItem.builder()
                            .product(product)
                            .price(product.getPrice() * purchaseItemDto.getQuantity())
                            .quantity(purchaseItemDto.getQuantity())
                            .delivered(false)
                            .build();
                })
                .collect(Collectors.toSet());

        double totalAmount = purchaseItems.stream().mapToDouble(PurchaseItem::getPrice).sum();

        Purchase purchase = Purchase.builder()
                .purchasedAt(LocalDateTime.now())
                .totalAmount(totalAmount)
                .buyer(buyer)
                .purchaseItems(purchaseItems)
                .build();

        BankTransactionRequestDto requestDto = BankTransactionRequestDto.builder()
                .senderAccountNumber(buyer.getAccountNumber())
                .receiverAccountNumber(ApplicationConstants.ORG_BANK_ACCOUNT)
                .amount(totalAmount)
                .build();

        ResponseEntity<BankTransactionResponseDto> bankResponse = bankClient.post()
                .uri("/transaction/transfer")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + ApplicationConstants.BANK_TOKEN)
                .body(Mono.just(requestDto), BankTransactionRequestDto.class)
                .retrieve()
                .toEntity(BankTransactionResponseDto.class)
                .block();

        if (bankResponse != null) {
            purchaseItems.forEach(purchaseItem -> {
                purchaseItem.setPurchase(purchase);
                Product product = purchaseItem.getProduct();
                product.setQuantity(product.getQuantity() - purchaseItem.getQuantity());
                purchaseItem.setProduct(productRepository.save(product));
            });
            purchase.setTransactionId(Objects.requireNonNull(bankResponse.getBody()).getId());
        } else {
            throw new TransactionFailedException("Transaction Failed!");
        }

        return PurchaseMapper.mapToPurchaseDto(purchaseRepository.save(purchase));
    }

    @Override
    public PurchasesResponseDto getPurchasesByBuyer(int buyerId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Purchase> purchasePage = purchaseRepository.findAllByBuyerId(buyerId, pageable);
        return PurchasesResponseDto.builder()
                .content(purchasePage.getContent().stream().map(PurchaseMapper::mapToPurchaseDto).toList())
                .pageNumber(purchasePage.getNumber())
                .pageSize(purchasePage.getSize())
                .totalElements(purchasePage.getTotalElements())
                .totalPages(purchasePage.getTotalPages())
                .last(purchasePage.isLast())
                .build();
    }

    @Override
    public DeliverDto getUnDeliveredPurchases(int sellerId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<PurchaseItem> purchaseItemPage = purchaseItemRepository.findAllByProductSellerIdAndDelivered(sellerId, false, pageable);
        return DeliverDto.builder()
                .content(purchaseItemPage.getContent().stream().map(PurchaseMapper::mapToDeliveryDto).toList())
                .pageNumber(purchaseItemPage.getNumber())
                .pageSize(purchaseItemPage.getSize())
                .totalElements(purchaseItemPage.getTotalElements())
                .totalPages(purchaseItemPage.getTotalPages())
                .last(purchaseItemPage.isLast())
                .build();
    }

    @Override
    public BankTransactionResponseDto validateTransaction(int transactionId) {
        ResponseEntity<BankTransactionResponseDto> bankResponse = bankClient.get()
                .uri("/transaction/" + transactionId)
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + ApplicationConstants.BANK_TOKEN)
                .retrieve()
                .toEntity(BankTransactionResponseDto.class)
                .block();

        if (bankResponse != null) {
            return bankResponse.getBody();
        } else {
            throw new TransactionNotFoundException("Transaction not found!");
        }
    }

    @Override
    public PurchaseDeliverDto deliverPurchases(int purchaseItemId) {
        PurchaseItem purchaseItem = purchaseItemRepository.findById(purchaseItemId).orElseThrow(() -> new PurchaseItemNotFoundException("Purchase not found"));

        BankTransactionRequestDto requestDto = BankTransactionRequestDto.builder()
                .senderAccountNumber(ApplicationConstants.ORG_BANK_ACCOUNT)
                .receiverAccountNumber(purchaseItem.getProduct().getSeller().getAccountNumber())
                .amount(purchaseItem.getPrice())
                .build();

        ResponseEntity<BankTransactionResponseDto> bankResponse = bankClient.post()
                .uri("/transaction/transfer")
                .header(HttpHeaders.AUTHORIZATION, "Bearer " + ApplicationConstants.BANK_TOKEN)
                .body(Mono.just(requestDto), BankTransactionRequestDto.class)
                .retrieve()
                .toEntity(BankTransactionResponseDto.class)
                .block();

        if (bankResponse != null) {
            purchaseItem.setDelivered(true);
            purchaseItemRepository.save(purchaseItem);
            return PurchaseMapper.mapToDeliveryDto(purchaseItem);
        } else {
            throw new TransactionFailedException("Transaction Failed!");
        }
    }

    @Override
    public void deletePurchase(int purchaseId) {
        Purchase purchase = purchaseRepository.findById(purchaseId).orElseThrow(() -> new PurchaseNotFoundException("Purchase not found!"));
        purchaseRepository.delete(purchase);
    }
}
