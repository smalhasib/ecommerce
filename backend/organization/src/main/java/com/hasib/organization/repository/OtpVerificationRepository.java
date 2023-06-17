package com.hasib.organization.repository;

import com.hasib.organization.model.OtpVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpVerificationRepository extends JpaRepository<OtpVerification, Integer> {
    Optional<OtpVerification> findOtpVerificationByUserId(int userId);

    Optional<OtpVerification> findOtpVerificationByAccountNumber(long accountNumber);
}
