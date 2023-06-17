package com.hasib.organization.repository;

import com.hasib.organization.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

    Boolean existsByAccountNumber(long accountNumber);

    Optional<UserEntity> findByAccountNumber(long accountNumber);
}
