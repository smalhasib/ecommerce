package com.hasib.organization.service;

import com.hasib.organization.dto.*;
import com.hasib.organization.dto.bank.BankTransactionResponseDto;

public interface PurchaseService {
    PurchaseResponseDto createPurchase(PurchaseDto purchaseDto);

    PurchasesResponseDto getPurchasesByBuyer(int buyerId, int pageNumber, int pageSize);

    DeliverDto getUnDeliveredPurchases(int sellerId, int pageNumber, int pageSize);

    BankTransactionResponseDto validateTransaction(int transactionId);

    PurchaseDeliverDto deliverPurchases(int purchaseItemId);

    void deletePurchase(int purchaseId);
}
