package com.hasib.bank.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public int sendOtpEmail(String recipientEmail) {
        int otp = new Random().nextInt(900000) + 100000;

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("alhasibsm@gmail.com");
        message.setTo(recipientEmail);
        message.setSubject("OTP Verification");
        message.setText("Your OTP is: " + otp);
        javaMailSender.send(message);

        return otp;
    }
}
