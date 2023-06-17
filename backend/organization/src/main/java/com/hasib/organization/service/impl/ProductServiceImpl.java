package com.hasib.organization.service.impl;

import com.hasib.organization.dto.ProductDto;
import com.hasib.organization.dto.ProductResponseDto;
import com.hasib.organization.dto.ProductsResponseDto;
import com.hasib.organization.exception.ProductNotFoundException;
import com.hasib.organization.exception.UserNotFoundException;
import com.hasib.organization.mapper.ProductMapper;
import com.hasib.organization.model.Category;
import com.hasib.organization.model.Product;
import com.hasib.organization.model.UserEntity;
import com.hasib.organization.repository.CategoryRepository;
import com.hasib.organization.repository.ProductRepository;
import com.hasib.organization.repository.UserRepository;
import com.hasib.organization.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.hasib.organization.mapper.ProductMapper.mapToDto;
import static com.hasib.organization.mapper.ProductMapper.mapToEntity;

@Service
public class ProductServiceImpl implements ProductService {

    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Autowired
    public ProductServiceImpl(UserRepository userRepository, ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
    }

    @Override
    public ProductResponseDto createProduct(ProductDto productDto) {
        UserEntity seller = userRepository.findById(productDto.getSellerId()).orElseThrow(() -> new UserNotFoundException("User not found!"));
        List<Category> selectedCategories = categoryRepository.findByNameIn(productDto.getCategories());

        Product product = Product.builder()
                .name(productDto.getName())
                .price(productDto.getPrice())
                .quantity(productDto.getQuantity())
                .seller(seller)
                .categories(selectedCategories)
                .build();

        Product savedProduct = productRepository.save(product);

        seller.getSellingProducts().add(savedProduct);
        userRepository.save(seller);

        return mapToDto(savedProduct);
    }

    @Override
    public ProductsResponseDto getAllProduct(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Product> productPage = productRepository.findAll(pageable);
        List<ProductResponseDto> products = productPage.getContent()
                .stream()
                .map(ProductMapper::mapToDto)
                .toList();
        return ProductsResponseDto.builder()
                .content(products)
                .pageNumber(productPage.getNumber())
                .pageSize(productPage.getSize())
                .totalElements(productPage.getTotalElements())
                .totalPages(productPage.getTotalPages())
                .last(productPage.isLast())
                .build();
    }

    @Override
    public ProductResponseDto getProductById(int id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found!"));
        return mapToDto(product);
    }

    @Override
    public ProductsResponseDto getProductsBySeller(int sellerId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Product> productPage = productRepository.findAllBySellerId(sellerId, pageable);
        List<ProductResponseDto> products = productPage.getContent()
                .stream()
                .map(ProductMapper::mapToDto)
                .toList();
        return ProductsResponseDto.builder()
                .content(products)
                .pageNumber(productPage.getNumber())
                .pageSize(productPage.getSize())
                .totalElements(productPage.getTotalElements())
                .totalPages(productPage.getTotalPages())
                .last(productPage.isLast())
                .build();
    }

    @Override
    public ProductsResponseDto getProductsByCategories(List<String> categories, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        List<Category> selectedCategories = categoryRepository.findByNameIn(categories);
        Page<Product> productPage = productRepository.findAllByCategoriesIn(Collections.singletonList(selectedCategories), pageable);
        List<ProductResponseDto> products = productPage.getContent()
                .stream()
                .map(ProductMapper::mapToDto)
                .toList();
        return ProductsResponseDto.builder()
                .content(products)
                .pageNumber(productPage.getNumber())
                .pageSize(productPage.getSize())
                .totalElements(productPage.getTotalElements())
                .totalPages(productPage.getTotalPages())
                .last(productPage.isLast())
                .build();
    }

    @Override
    public ProductResponseDto updateProduct(ProductDto productDto, int id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found!"));
        List<Category> selectedCategories = categoryRepository.findByNameIn(productDto.getCategories());

        product = mapToEntity(productDto);
        product.setCategories(selectedCategories);

        return mapToDto(productRepository.save(product));
    }

    @Override
    public void deleteProduct(int id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("Product not found!"));
        productRepository.delete(product);
    }
}
