package com.hasib.organization.repository;

import com.hasib.organization.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {
    Page<Product> findAllBySellerId(int sellerId, Pageable pageable);

    List<Product> findAllByIdIn(List<Integer> productIds);
}
