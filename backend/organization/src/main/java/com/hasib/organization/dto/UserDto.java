package com.hasib.organization.dto;

import com.hasib.organization.model.Role;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UserDto {
    private int id;
    private String name;
    private String email;
    private String username;
    private long accountNumber;
    private List<Role> roles;
    private boolean isVerified;
}
