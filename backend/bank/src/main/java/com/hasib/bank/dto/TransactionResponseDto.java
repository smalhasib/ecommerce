package com.hasib.bank.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TransactionResponseDto {
    private int id;
    private TransactedUserDto sender;
    private TransactedUserDto receiver;
    private long amount;
    private LocalDateTime createdAt;
}
