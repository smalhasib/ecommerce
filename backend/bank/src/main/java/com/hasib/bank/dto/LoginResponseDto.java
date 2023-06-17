package com.hasib.bank.dto;

import lombok.Data;

@Data
public class LoginResponseDto {
    private final String accessToken;
    private final String tokenType = "Bearer ";
    private final UserDto userDto;

    public LoginResponseDto(String accessToken, UserDto userDto) {
        this.accessToken = accessToken;
        this.userDto = userDto;
    }
}
