package com.hasib.bank.service;

import com.hasib.bank.model.OtpVerification;

public interface OtpVerificationService {
    void createOtp(int userId, int otp);

    OtpVerification updateOtp(OtpVerification otpVerification, int id);

    void deleteOtp(int id);

    OtpVerification getOtpVerificationByUserId(int userId);
}
