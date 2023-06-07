package com.hasib.bank.util;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class EmailService {
    private final JavaMailSender javaMailSender;

    @Autowired
    public EmailService(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    }

    public int sendOtpEmail(String recipientEmail) throws MessagingException {
        int otp = new Random().nextInt(900000) + 100000;

        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
        String message = "<h1>Your OTP is: " + otp + "</h1>";

        helper.setFrom("alhasibsm@gmail.com");
        helper.setTo(recipientEmail);
        helper.setSubject("OTP Verification");
        helper.setText(message, true);
        javaMailSender.send(mimeMessage);

        return otp;
    }
}
