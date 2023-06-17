package com.hasib.organization.dto;

import lombok.Data;

@Data
public class VerifyDto {
    private int userId;
    private long accountNumber;
    private int otp;
}
