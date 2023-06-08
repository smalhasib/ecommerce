package com.hasib.bank.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TransactionRequestDto {
    private int senderAccountNumber;
    private int receiverAccountNumber;
    private long amount;
}
