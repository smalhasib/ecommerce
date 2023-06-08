package com.hasib.bank.mapper;

import com.hasib.bank.dto.UserDto;
import com.hasib.bank.model.UserEntity;

public class UserMapper {

    public static UserDto mapToDto(UserEntity entity) {
        return UserDto.builder()
                .id(entity.getId())
                .name(entity.getName())
                .nid(entity.getNid())
                .address(entity.getAddress())
                .email(entity.getEmail())
                .username(entity.getUsername())
                .accountNumber(entity.getAccountNumber())
                .roles(entity.getRoles())
                .isVerified(entity.isVerified())
                .money(entity.getMoney())
                .build();
    }

    public static UserEntity mapToEntity(UserDto userDto, UserEntity oldUserEntity) {
        return UserEntity.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .nid(userDto.getNid())
                .address(userDto.getAddress())
                .email(userDto.getEmail())
                .username(userDto.getUsername())
                .password(oldUserEntity.getPassword())
                .accountNumber(userDto.getAccountNumber())
                .roles(userDto.getRoles())
                .isVerified(userDto.isVerified())
                .money(userDto.getMoney())
                .sentTransactions(oldUserEntity.getSentTransactions())
                .receivedTransactions(oldUserEntity.getReceivedTransactions())
                .build();
    }
}
