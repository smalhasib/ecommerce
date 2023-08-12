package com.hasib.organization.mapper;

import com.hasib.organization.dto.ProductDto;
import com.hasib.organization.dto.ProductResponseDto;
import com.hasib.organization.model.Product;

public class ProductMapper {

    public static ProductResponseDto mapToDto(Product product) {
        return ProductResponseDto.builder()
                .id(product.getId())
                .seller(UserMapper.mapToUserDto(product.getSeller()))
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .name(product.getName())
                .description(product.getDescription())
                .imageUrl(product.getImageUrl())
                .build();
    }

    public static Product mapToEntity(ProductDto productDto) {
        return Product.builder()
                .name(productDto.getName())
                .quantity(productDto.getQuantity())
                .price(productDto.getPrice())
                .description(productDto.getDescription())
                .imageUrl(productDto.getImageUrl())
                .build();
    }
}
