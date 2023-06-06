package com.hasib.bank.dto;

import com.hasib.bank.model.Address;
import lombok.Data;

@Data
public class RegisterDto {
    private String name;
    private long nid;
    private Address address;
    private String email;
    private String username;
    private String password;
}
