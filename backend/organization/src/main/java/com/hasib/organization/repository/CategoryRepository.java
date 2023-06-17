package com.hasib.organization.repository;

import com.hasib.organization.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
    List<Category> findByNameIn(List<String> categories);
}
