package com.hasib.bank.dto;

import lombok.Data;

@Data
public class VerifyDto {
    private int userId;
    private long cardNumber;
    private int otp;
}
