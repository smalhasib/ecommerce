package com.hasib.organization.dto.bank;

import lombok.*;

import java.time.LocalDateTime;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankTransactionResponseDto {
    private int id;
    private BankTransactedUserDto sender;
    private BankTransactedUserDto receiver;
    private long amount;
    private LocalDateTime createdAt;
}
