package com.hasib.organization.service;

import com.hasib.organization.dto.RegisterDto;
import com.hasib.organization.dto.UserDto;
import com.hasib.organization.dto.UsersResponseDto;

public interface UserService {

    UserDto createUser(RegisterDto registerDto);

    UsersResponseDto getAllUsers(int pageNumber, int pageSize);

    UserDto getUserById(int id);

    UserDto updateUser(UserDto userDto, int id);

    void deleteUser(int id);

    boolean userExistsById(int id);

    boolean userExistsByEmail(String email);

    boolean userExistsByUsername(String username);

    void deleteFailedCreatedUser(UserDto userDto);

    UserDto getUserByAccountNumber(long accountNumber);

    UserDto getUserByUsername(String username);
}
