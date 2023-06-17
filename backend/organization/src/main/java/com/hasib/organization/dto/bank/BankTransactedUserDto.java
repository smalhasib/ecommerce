package com.hasib.organization.dto.bank;

import lombok.*;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankTransactedUserDto {
    private int id;
    private String name;
    private String email;
    private long accountNumber;
}
