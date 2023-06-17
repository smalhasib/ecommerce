package com.hasib.organization.repository;

import com.hasib.organization.model.Category;
import com.hasib.organization.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Integer> {

    Page<Product> findAllByCategoriesIn(Collection<List<Category>> categories, Pageable pageable);

    Page<Product> findAllBySellerId(int sellerId, Pageable pageable);

    List<Product> findAllByIdIn(List<Integer> productIds);
}
