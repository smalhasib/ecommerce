package com.hasib.bank.service;

import com.hasib.bank.dto.RegisterDto;
import com.hasib.bank.dto.UserDto;
import com.hasib.bank.dto.UsersResponseDto;
import com.hasib.bank.model.UserEntity;

public interface UserService {

    UserDto createUser(RegisterDto registerDto);

    UsersResponseDto getAllUsers(int pageNumber, int pageSize);

    UserDto getUserById(int id);

    UserDto updateUser(UserDto userDto, int id);

    void deleteUser(int id);

    boolean userExistsById(int id);

    boolean userExistsByNid(long nid);

    boolean userExistsByEmail(String email);

    boolean userExistsByUsername(String username);

    boolean userExistsByAccountNumber(long accountNumber);

    void deleteFailedCreatedUser(UserDto userDto);

    UserDto getUserByAccountNumber(long accountNumber);

    UserDto getUserByUsername(String username);
}
