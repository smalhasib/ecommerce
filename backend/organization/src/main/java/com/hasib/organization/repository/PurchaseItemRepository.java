package com.hasib.organization.repository;

import com.hasib.organization.model.PurchaseItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseItemRepository extends JpaRepository<PurchaseItem, Integer> {

    Page<PurchaseItem> findAllByProductSellerIdAndDelivered(int sellerId, boolean deliver, Pageable pageable);
}
