package com.hasib.bank.service.impl;

import com.hasib.bank.exception.OtpVerificationNotFoundException;
import com.hasib.bank.model.OtpVerification;
import com.hasib.bank.repository.OtpVerificationRepository;
import com.hasib.bank.service.OtpVerificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class OtpVerificationServiceImpl implements OtpVerificationService {

    private final OtpVerificationRepository otpVerificationRepository;

    @Autowired
    public OtpVerificationServiceImpl(OtpVerificationRepository otpVerificationRepository) {
        this.otpVerificationRepository = otpVerificationRepository;
    }

    @Override
    public void createUserVerificationOtp(int userId, int otp) {
        OtpVerification otpVerification = OtpVerification.builder()
                .userId(userId)
                .otp(otp)
                .expirationTime(LocalDateTime.now().plus(Duration.ofMinutes(2)))
                .build();
        otpVerificationRepository.save(otpVerification);
    }

    @Override
    public void createAccountVerificationOtp(long accountNumber, int otp) {
        Optional<OtpVerification> oldOtpVerification = otpVerificationRepository.findOtpVerificationByAccountNumber(accountNumber);
        if (oldOtpVerification.isPresent()) {
            oldOtpVerification.get().setOtp(otp);
            oldOtpVerification.get().setExpirationTime(LocalDateTime.now().plus(Duration.ofMinutes(2)));
            otpVerificationRepository.save(oldOtpVerification.get());
        } else {
            OtpVerification otpVerification = OtpVerification.builder()
                    .accountNumber(accountNumber)
                    .otp(otp)
                    .expirationTime(LocalDateTime.now().plus(Duration.ofMinutes(2)))
                    .build();
            otpVerificationRepository.save(otpVerification);
        }
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
    public OtpVerification getOtpVerificationByAccountNumber(long accountNumber) {
        return otpVerificationRepository.findOtpVerificationByAccountNumber(accountNumber).orElseThrow(() -> new OtpVerificationNotFoundException("Otp not found"));
    }

    @Override
    public void deleteOtp(int id) {
        OtpVerification otpVerification = otpVerificationRepository.findById(id).orElseThrow(() -> new OtpVerificationNotFoundException("Otp not found"));
        otpVerificationRepository.delete(otpVerification);
    }
}
