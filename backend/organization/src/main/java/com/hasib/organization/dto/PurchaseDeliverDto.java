package com.hasib.organization.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PurchaseDeliverDto {
    private int purchaseId;
    private int purchaseItemId;
    private int transactionId;
    private String buyerName;
    private ProductResponseDto product;
    private double amount;
    private double quantity;
    private boolean delivered;
}
