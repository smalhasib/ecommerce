package com.hasib.bank.service;

import com.hasib.bank.model.OtpVerification;

public interface OtpVerificationService {
    void createUserVerificationOtp(int userId, int otp);

    void createAccountVerificationOtp(long accountNumber, int otp);

    OtpVerification updateOtp(OtpVerification otpVerification, int id);

    void deleteOtp(int id);

    OtpVerification getOtpVerificationByUserId(int userId);

    OtpVerification getOtpVerificationByAccountNumber(long accountNumber);
}
