package com.hasib.bank.dto;

import com.hasib.bank.model.Address;
import com.hasib.bank.model.Role;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Builder
@Data
public class UserDto {
    private int id;
    private String name;
    private long nid;
    private Address address;
    private String email;
    private String username;
    private long accountNumber;
    private List<Role> roles;
    private boolean isVerified;
    private double money;
}
