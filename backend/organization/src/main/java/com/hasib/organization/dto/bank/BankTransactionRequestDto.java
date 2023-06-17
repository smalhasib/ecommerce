package com.hasib.organization.dto.bank;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BankTransactionRequestDto {
    private long senderAccountNumber;
    private long receiverAccountNumber;
    private double amount;
}
