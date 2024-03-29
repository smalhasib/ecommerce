package com.hasib.organization.controller;

import com.hasib.organization.dto.*;
import com.hasib.organization.model.OtpVerification;
import com.hasib.organization.security.JwtGenerator;
import com.hasib.organization.service.OtpVerificationService;
import com.hasib.organization.service.UserService;
import com.hasib.organization.util.EmailService;
import jakarta.mail.MessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final OtpVerificationService otpVerificationService;
    private final JwtGenerator jwtGenerator;
    private final EmailService emailService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserService userService, JwtGenerator jwtGenerator, EmailService emailService, OtpVerificationService otpVerificationService) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.otpVerificationService = otpVerificationService;
        this.jwtGenerator = jwtGenerator;
        this.emailService = emailService;
    }

    @PostMapping("register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterDto registerDto) {
        System.out.println(registerDto);
        RegisterResponseDto responseDto = new RegisterResponseDto();
        if (userService.userExistsByEmail(registerDto.getEmail())) {
            responseDto.setMessage("Duplicate Email found!");
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }
        if (userService.userExistsByUsername(registerDto.getUsername())) {
            responseDto.setMessage("Username is taken!");
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }

        UserDto savedUser = userService.createUser(registerDto);

        try {
            otpVerificationService.createUserVerificationOtp(savedUser.getId(), emailService.sendOtpEmail(savedUser.getEmail()));
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
        UserDto loggedInUser = userService.getUserByUsername(loginDto.getUsername());
        return ResponseEntity.ok(new LoginResponseDto(token, loggedInUser));
    }

    @PostMapping("verify")
    public ResponseEntity<String> verify(@RequestBody VerifyDto verifyDto) {
        if (!userService.userExistsById(verifyDto.getUserId())) {
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
            otpVerificationService.deleteOtp(otpVerification.getId());
            return ResponseEntity.ok("Verification successful");
        }

        return new ResponseEntity<>("Verification failed", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("resend-otp")
    public ResponseEntity<String> resendOtp(@RequestBody Map<String, Integer> body) throws MessagingException {
        int userId = body.get("userId");
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
