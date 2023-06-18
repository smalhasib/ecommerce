package com.hasib.organization.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProductDto {
    private String name;
    private String description;
    private String imageUrl;
    private List<String> categories;
    private double quantity;
    private double price;
    private int sellerId;
}
