package com.hasib.bank.service.impl;

import com.hasib.bank.dto.RegisterDto;
import com.hasib.bank.dto.UserDto;
import com.hasib.bank.dto.UsersResponseDto;
import com.hasib.bank.exception.UserNotFoundException;
import com.hasib.bank.mapper.UserMapper;
import com.hasib.bank.model.Role;
import com.hasib.bank.model.UserEntity;
import com.hasib.bank.repository.RoleRepository;
import com.hasib.bank.repository.UserRepository;
import com.hasib.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.hasib.bank.mapper.UserMapper.mapToEntity;
import static com.hasib.bank.mapper.UserMapper.mapToUserDto;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDto createUser(RegisterDto registerDto) {
        Role roles = roleRepository.findByName("USER").get();
        UserEntity user = UserEntity.builder()
                .name(registerDto.getName())
                .nid(registerDto.getNid())
                .address(registerDto.getAddress())
                .email(registerDto.getEmail())
                .username(registerDto.getUsername())
                .password(passwordEncoder.encode((registerDto.getPassword())))
                .roles(Collections.singletonList(roles))
                .build();
        return mapToUserDto(userRepository.save(user));
    }

    @Override
    public UsersResponseDto getAllUsers(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<UserEntity> userEntityPage = userRepository.findAll(pageable);
        List<UserDto> userDtoList = userEntityPage.getContent()
                .stream()
                .map(UserMapper::mapToUserDto)
                .toList();
        return UsersResponseDto.builder()
                .content(userDtoList)
                .pageNumber(userEntityPage.getNumber())
                .pageSize(userEntityPage.getSize())
                .totalElements(userEntityPage.getTotalElements())
                .totalPages(userEntityPage.getTotalPages())
                .last(userEntityPage.isLast())
                .build();
    }

    @Override
    public UserDto getUserById(int id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        return mapToUserDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, int id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        user = mapToEntity(userDto, user);
        return mapToUserDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(int id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.delete(user);
    }

    @Override
    public boolean userExistsById(int id) {
        return userRepository.existsById(id);
    }

    @Override
    public boolean userExistsByNid(long nid) {
        return userRepository.existsByNid(nid);
    }

    @Override
    public boolean userExistsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean userExistsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public boolean userExistsByAccountNumber(long accountNumber) {
        return userRepository.existsByAccountNumber(accountNumber);
    }

    @Override
    public void deleteFailedCreatedUser(UserDto userDto) {
        UserEntity user = userRepository.findById(userDto.getId()).orElseThrow(() -> new UserNotFoundException("User not found"));
        userRepository.delete(user);
    }

    @Override
    public UserDto getUserByAccountNumber(long accountNumber) {
        UserEntity user = userRepository.findByAccountNumber(accountNumber).orElseThrow(() -> new UserNotFoundException("User not found"));
        return mapToUserDto(user);
    }

    @Override
    public UserDto getUserByUsername(String username) {
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found"));
        return mapToUserDto(user);
    }
}
