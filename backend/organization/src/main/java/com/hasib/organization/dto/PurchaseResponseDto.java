package com.hasib.organization.dto;

import com.hasib.organization.model.PurchaseItem;
import com.hasib.organization.model.UserEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class PurchaseResponseDto {
    private int id;
    private UserEntity buyer;
    private Set<PurchaseItem> purchaseItems;
    private double totalAmount;
    private int transactionId;
    private LocalDateTime purchasedAt;
}
