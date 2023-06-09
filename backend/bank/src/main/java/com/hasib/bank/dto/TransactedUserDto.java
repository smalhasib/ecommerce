package com.hasib.bank.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TransactedUserDto {
    private int id;
    private String name;
    private String email;
    private long accountNumber;
}
