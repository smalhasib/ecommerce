package com.hasib.bank.service;

import com.hasib.bank.dto.RegisterDto;
import com.hasib.bank.dto.UserDto;
import com.hasib.bank.dto.UsersResponseDto;
import com.hasib.bank.model.UserEntity;

public interface UserService {

    UserEntity createUser(RegisterDto registerDto);

    UsersResponseDto getAllUsers(int pageNumber, int pageSize);

    UserDto getUserById(int id);

    UserDto updateUser(UserDto userDto, int id);

    void deleteUser(int id);

    boolean userExistById(int id);

    boolean userExistByNid(long nid);

    boolean userExistByEmail(String email);

    boolean userExistByUsername(String username);

    void deleteFailedCreatedUser(UserEntity user);

    UserEntity getUserByUsername(String username);
}
