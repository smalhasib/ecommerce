package com.hasib.organization.dto;

import lombok.Data;

@Data
public class RegisterDto {
    private String name;
    private String email;
    private String username;
    private String password;
    private String type;
}
