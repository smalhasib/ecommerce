package com.hasib.organization.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class ProductResponseDto {
    private int id;
    private String name;
    private String description;
    private String imageUrl;
    private double quantity;
    private double price;
    private UserDto seller;
}
