package com.hasib.organization.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class DeliverDto {
    private List<PurchaseDeliverDto> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
