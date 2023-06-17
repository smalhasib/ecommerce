package com.hasib.bank.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionRequestDto {
    private long senderAccountNumber;
    private long receiverAccountNumber;
    private double amount;
}
