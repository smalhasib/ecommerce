package com.hasib.bank.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String name;
    private long nid;
    private String address;
    private String email;
    private String username;
    private String password;
}
