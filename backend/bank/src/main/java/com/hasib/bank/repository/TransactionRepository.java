package com.hasib.bank.repository;

import com.hasib.bank.model.Transaction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    Page<Transaction> findAllBySenderId(int senderId, Pageable pageable);

    Page<Transaction> findAllByReceiverId(int receiverId, Pageable pageable);

    Page<Transaction> findAllBySenderIdOrReceiverId(int senderId, int receiverId, Pageable pageable);
}
