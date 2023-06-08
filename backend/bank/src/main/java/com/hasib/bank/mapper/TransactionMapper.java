package com.hasib.bank.mapper;

import com.hasib.bank.dto.TransactionResponseDto;
import com.hasib.bank.model.Transaction;

public class TransactionMapper {

    public static TransactionResponseDto mapToDto(Transaction transaction) {
        return TransactionResponseDto.builder()
                .id(transaction.getId())
                .sender(transaction.getSender())
                .receiver(transaction.getReceiver())
                .amount(transaction.getAmount())
                .createdAt(transaction.getCreatedAt())
                .build();
    }
}
