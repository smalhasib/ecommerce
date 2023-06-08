package com.hasib.bank.dto;

import com.hasib.bank.model.UserEntity;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class TransactionResponseDto {
    private int id;
    private UserEntity sender;
    private UserEntity receiver;
    private long amount;
    private LocalDateTime createdAt;
}
