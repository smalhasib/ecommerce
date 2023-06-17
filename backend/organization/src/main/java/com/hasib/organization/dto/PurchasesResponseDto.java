package com.hasib.organization.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class PurchasesResponseDto {
    private List<PurchaseResponseDto> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
