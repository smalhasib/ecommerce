package com.hasib.bank.dto;

import lombok.Data;

@Data
public class LoginResponseDto {
    private final String accessToken;
    private final String tokenType = "Bearer ";

    public LoginResponseDto(String accessToken) {
        this.accessToken = accessToken;
    }
}
