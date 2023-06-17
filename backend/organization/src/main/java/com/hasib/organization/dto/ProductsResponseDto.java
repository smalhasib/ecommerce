package com.hasib.organization.dto;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ProductsResponseDto {
    private List<ProductResponseDto> content;
    private int pageNumber;
    private int pageSize;
    private long totalElements;
    private int totalPages;
    private boolean last;
}
