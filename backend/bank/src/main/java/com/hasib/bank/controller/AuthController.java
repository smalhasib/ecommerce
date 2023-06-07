package com.hasib.bank.controller;

import com.hasib.bank.dto.*;
import com.hasib.bank.model.OtpVerification;
import com.hasib.bank.model.UserEntity;
import com.hasib.bank.security.JwtGenerator;
import com.hasib.bank.service.OtpVerificationService;
import com.hasib.bank.service.UserService;
import com.hasib.bank.util.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final OtpVerificationService otpVerificationService;
    private final JwtGenerator jwtGenerator;
    private final EmailService emailService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserService userService, PasswordEncoder passwordEncoder, JwtGenerator jwtGenerator, EmailService emailService, OtpVerificationService otpVerificationService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.otpVerificationService = otpVerificationService;
        this.jwtGenerator = jwtGenerator;
        this.emailService = emailService;
    }

    @PostMapping("register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterDto registerDto) {
        RegisterResponseDto responseDto = new RegisterResponseDto();
        if (userService.userExistByNid(registerDto.getNid())) {
            responseDto.setMessage("Duplicate NID found!");
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }
        if (userService.userExistByEmail(registerDto.getEmail())) {
            responseDto.setMessage("Duplicate Email found!");
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }
        if (userService.userExistByUsername(registerDto.getUsername())) {
            responseDto.setMessage("Username is taken!");
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }

        UserEntity savedUser = userService.createUser(registerDto);

        try {
            otpVerificationService.createOtp(savedUser.getId(), emailService.sendOtpEmail(savedUser.getEmail()));
        } catch (Exception e) {
            e.printStackTrace();
            userService.deleteFailedCreatedUser(savedUser);
            responseDto.setMessage("Otp send failed");
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }

        responseDto.setMessage("User registered success! Please verify your email");
        responseDto.setUserId(savedUser.getId());
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping("login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginDto loginDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginDto.getUsername(),
                        loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication);
        return ResponseEntity.ok(new LoginResponseDto(token));
    }

    @PostMapping("verify")
    public ResponseEntity<String> verify(@RequestBody VerifyDto verifyDto) {
        if (!userService.userExistById(verifyDto.getUserId())) {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }

        OtpVerification otpVerification = otpVerificationService.getOtpVerificationByUserId(verifyDto.getUserId());

        if (!LocalDateTime.now().isBefore(otpVerification.getExpirationTime())) {
            return new ResponseEntity<>("OTP timeout!", HttpStatus.BAD_REQUEST);
        }
        if (otpVerification.getOtp() == verifyDto.getOtp()) {
            UserDto user = userService.getUserById(verifyDto.getUserId());
            user.setVerified(true);
            userService.updateUser(user, verifyDto.getUserId());
            otpVerificationService.deleteOtp(verifyDto.getUserId());
            return ResponseEntity.ok("Verification successful");
        }

        return new ResponseEntity<>("Verification failed", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("resend-otp")
    public ResponseEntity<String> resendOtp(@RequestParam int userId) throws MessagingException {
        UserDto user = userService.getUserById(userId);
        OtpVerification otpVerification = otpVerificationService.getOtpVerificationByUserId(userId);
        otpVerification.setOtp(emailService.sendOtpEmail(user.getEmail()));
        otpVerification.setExpirationTime(LocalDateTime.now().plus(Duration.ofMinutes(2)));

        if (otpVerificationService.updateOtp(otpVerification, userId) != null) {
            return ResponseEntity.ok("OTP resent");
        }

        return new ResponseEntity<>("OTP resend failed", HttpStatus.BAD_REQUEST);
    }
}
