package com.hasib.organization.mapper;

import com.hasib.organization.dto.UserDto;
import com.hasib.organization.model.UserEntity;

public class UserMapper {

    public static UserDto mapToUserDto(UserEntity entity) {
        return UserDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .email(entity.getEmail())
                .username(entity.getUsername())
                .accountNumber(entity.getAccountNumber())
                .roles(entity.getRoles())
                .isVerified(entity.isVerified())
                .build();
    }

    public static UserEntity mapToEntity(UserDto userDto, UserEntity oldUserEntity) {
        return UserEntity.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .password(oldUserEntity.getPassword())
                .accountNumber(userDto.getAccountNumber())
                .roles(userDto.getRoles())
                .isVerified(userDto.isVerified())
                .build();
    }
}
