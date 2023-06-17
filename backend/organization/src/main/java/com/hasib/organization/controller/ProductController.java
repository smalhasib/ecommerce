package com.hasib.organization.controller;

import com.hasib.organization.dto.ProductDto;
import com.hasib.organization.dto.ProductResponseDto;
import com.hasib.organization.dto.ProductsResponseDto;
import com.hasib.organization.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product/")
public class ProductController {

    public final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/")
    public ResponseEntity<ProductsResponseDto> getProducts(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return ResponseEntity.ok(productService.getAllProduct(pageNumber, pageSize));
    }

    @GetMapping("{id}")
    public ResponseEntity<ProductResponseDto> ProductDetail(@PathVariable int id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @GetMapping("/by-seller")
    public ResponseEntity<ProductsResponseDto> getProductsBySeller(
            @RequestParam int sellerId,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return ResponseEntity.ok(productService.getProductsBySeller(sellerId, pageNumber, pageSize));
    }

    @GetMapping("/by-categories")
    public ResponseEntity<ProductsResponseDto> getProductsBySeller(
            @RequestParam List<String> categories,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize) {
        return ResponseEntity.ok(productService.getProductsByCategories(categories, pageNumber, pageSize));
    }

    @PostMapping("create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<ProductResponseDto> createProduct(@RequestBody ProductDto productDto) {
        return new ResponseEntity<>(productService.createProduct(productDto), HttpStatus.CREATED);
    }

    @PutMapping("{id}/update")
    public ResponseEntity<ProductResponseDto> updateProduct(@RequestBody ProductDto crateProductDto, @PathVariable("id") int ProductId) {
        return ResponseEntity.ok(productService.updateProduct(crateProductDto, ProductId));
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteProduct(@PathVariable("id") int productId) {
        productService.deleteProduct(productId);
        return ResponseEntity.ok("Product deleted successfully ");
    }
}
