package com.hasib.organization.mapper;

import com.hasib.organization.dto.PurchaseDeliverDto;
import com.hasib.organization.dto.PurchaseResponseDto;
import com.hasib.organization.model.Purchase;
import com.hasib.organization.model.PurchaseItem;

public class PurchaseMapper {

    public static PurchaseResponseDto mapToPurchaseDto(Purchase purchase) {
        return PurchaseResponseDto.builder()
                .id(purchase.getId())
                .purchasedAt(purchase.getPurchasedAt())
                .purchaseItems(purchase.getPurchaseItems())
                .transactionId(purchase.getTransactionId())
                .totalAmount(purchase.getTotalAmount())
                .buyer(purchase.getBuyer())
                .build();
    }

    public static PurchaseDeliverDto mapToDeliveryDto(PurchaseItem purchaseItem) {
        return PurchaseDeliverDto.builder()
                .purchaseId(purchaseItem.getPurchase().getId())
                .amount(purchaseItem.getPrice())
                .purchaseItemId(purchaseItem.getId())
                .product(ProductMapper.mapToDto(purchaseItem.getProduct()))
                .buyerName(purchaseItem.getPurchase().getBuyer().getName())
                .quantity(purchaseItem.getQuantity())
                .transactionId(purchaseItem.getPurchase().getTransactionId())
                .delivered(purchaseItem.isDelivered())
                .build();
    }
}
