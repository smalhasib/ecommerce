package com.hasib.bank.service.impl;

import com.hasib.bank.dto.RegisterDto;
import com.hasib.bank.dto.UserDto;
import com.hasib.bank.dto.UsersResponseDto;
import com.hasib.bank.exception.UserNotFoundException;
import com.hasib.bank.mapper.UserMapper;
import com.hasib.bank.model.Address;
import com.hasib.bank.model.Role;
import com.hasib.bank.model.UserEntity;
import com.hasib.bank.repository.AddressRepository;
import com.hasib.bank.repository.RoleRepository;
import com.hasib.bank.repository.UserRepository;
import com.hasib.bank.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

import static com.hasib.bank.mapper.UserMapper.mapToDto;
import static com.hasib.bank.mapper.UserMapper.mapToEntity;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final AddressRepository addressRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, AddressRepository addressRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.addressRepository = addressRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity createUser(RegisterDto registerDto) {
        Role roles = roleRepository.findByName("USER").get();
        Address savedAddress = addressRepository.save(registerDto.getAddress());
        UserEntity user = UserEntity.builder()
                .name(registerDto.getName())
                .nid(registerDto.getNid())
                .address(savedAddress)
                .email(registerDto.getEmail())
                .username(registerDto.getUsername())
                .password(passwordEncoder.encode((registerDto.getPassword())))
                .roles(Collections.singletonList(roles))
                .build();
        return userRepository.save(user);
    }

    @Override
    public UsersResponseDto getAllUsers(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<UserEntity> userEntityPage = userRepository.findAll(pageable);
        List<UserDto> userDtoList = userEntityPage.getContent()
                .stream()
                .map(UserMapper::mapToDto)
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
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        return mapToDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, int id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        user = mapToEntity(userDto, user);
        return mapToDto(userRepository.save(user));
    }

    @Override
    public void deleteUser(int id) {
        UserEntity user = userRepository.findById(id).orElseThrow(() -> new UserNotFoundException("User could not be found"));
        userRepository.delete(user);
    }

    @Override
    public boolean userExistById(int id) {
        return userRepository.existsById(id);
    }

    @Override
    public boolean userExistByNid(long nid) {
        return userRepository.existsByNid(nid);
    }

    @Override
    public boolean userExistByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    @Override
    public boolean userExistByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public void deleteFailedCreatedUser(UserEntity user) {
        addressRepository.delete(user.getAddress());
        userRepository.delete(user);
    }

    @Override
    public UserEntity getUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
