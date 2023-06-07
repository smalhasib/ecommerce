package com.hasib.bank.service.impl;

import com.hasib.bank.exception.OtpVerificationNotFoundException;
import com.hasib.bank.model.OtpVerification;
import com.hasib.bank.repository.OtpVerificationRepository;
import com.hasib.bank.service.OtpVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;

@Service
public class OtpVerificationServiceImpl implements OtpVerificationService {

    private final OtpVerificationRepository otpVerificationRepository;

    @Autowired
    public OtpVerificationServiceImpl(OtpVerificationRepository otpVerificationRepository) {
        this.otpVerificationRepository = otpVerificationRepository;
    }

    @Override
    public void createOtp(int userId, int otp) {
        OtpVerification otpVerification = OtpVerification.builder()
                .userId(userId)
                .otp(otp)
                .expirationTime(LocalDateTime.now().plus(Duration.ofMinutes(2)))
                .build();
        otpVerificationRepository.save(otpVerification);
    }

    @Override
    public OtpVerification updateOtp(OtpVerification otpVerification, int id) {
        otpVerificationRepository.findById(id).orElseThrow(() -> new OtpVerificationNotFoundException("Otp not found"));
        return otpVerificationRepository.save(otpVerification);
    }

    @Override
    public OtpVerification getOtpVerificationByUserId(int userId) {
        return otpVerificationRepository.findOtpVerificationByUserId(userId).orElseThrow(() -> new OtpVerificationNotFoundException("Otp not found"));
    }

    @Override
    public void deleteOtp(int id) {
        OtpVerification otpVerification = otpVerificationRepository.findById(id).orElseThrow(() -> new OtpVerificationNotFoundException("Otp not found"));
        otpVerificationRepository.delete(otpVerification);
    }
}
