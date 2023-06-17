package com.hasib.organization.dto.bank;

import com.hasib.organization.model.Role;
import lombok.*;

import java.util.List;

@Builder
@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BankUserDto {
    private int id;
    private String name;
    private long nid;
    private String address;
    private String email;
    private String username;
    private long accountNumber;
    private List<Role> roles;
    private boolean isVerified;
    private double money;
}
