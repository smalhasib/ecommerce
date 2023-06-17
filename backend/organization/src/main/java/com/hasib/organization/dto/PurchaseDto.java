package com.hasib.organization.dto;

import lombok.Data;

import java.util.List;

@Data
public class PurchaseDto {
    private List<PurchaseItemDto> purchases;
    private int buyerId;
}
