package com.hasib.organization.dto;

import com.hasib.organization.model.Category;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class ProductResponseDto {
    private int id;
    private String name;
    private List<Category> categories;
    private double quantity;
    private double price;
    private UserDto seller;
}
