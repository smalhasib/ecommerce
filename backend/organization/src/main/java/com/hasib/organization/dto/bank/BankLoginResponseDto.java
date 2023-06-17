package com.hasib.organization.dto.bank;

import lombok.*;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BankLoginResponseDto {
    private final String tokenType = "Bearer ";
    private String accessToken;
    private BankUserDto userDto;
}
