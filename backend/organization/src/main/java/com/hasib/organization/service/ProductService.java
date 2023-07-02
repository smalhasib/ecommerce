package com.hasib.organization.service;

import com.hasib.organization.dto.ProductDto;
import com.hasib.organization.dto.ProductResponseDto;
import com.hasib.organization.dto.ProductsResponseDto;

public interface ProductService {
    ProductResponseDto createProduct(ProductDto pokemonDto);

    ProductsResponseDto getAllProduct(int pageNumber, int pageSize);

    ProductResponseDto getProductById(int id);

    ProductsResponseDto getProductsBySeller(int sellerId, int pageNumber, int pageSize);

    ProductResponseDto updateProduct(ProductDto pokemonDto, int id);

    void deleteProduct(int id);
}
