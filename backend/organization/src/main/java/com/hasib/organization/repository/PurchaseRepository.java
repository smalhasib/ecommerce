package com.hasib.organization.repository;

import com.hasib.organization.model.Purchase;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, Integer> {
    Page<Purchase> findAllByBuyerId(int buyerId, Pageable pageable);
}
