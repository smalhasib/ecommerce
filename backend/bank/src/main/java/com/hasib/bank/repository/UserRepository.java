package com.hasib.bank.repository;

import com.hasib.bank.model.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {
    Optional<UserEntity> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByNid(long nid);

    Boolean existsByEmail(String email);

    Boolean existsByAccountNumber(long accountNumber);

    Optional<UserEntity> getUserEntitiesByAccountNumber(long accountNumber);
}
