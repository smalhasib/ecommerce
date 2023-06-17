package com.hasib.organization.dto.bank;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BankLoginDto {
    private String username;
    private String password;
}
