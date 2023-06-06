package com.hasib.bank.controller;

import com.hasib.bank.dto.*;
import com.hasib.bank.model.Address;
import com.hasib.bank.model.OtpVerification;
import com.hasib.bank.model.Role;
import com.hasib.bank.model.UserEntity;
import com.hasib.bank.repository.AddressRepository;
import com.hasib.bank.repository.OtpVerificationRepository;
import com.hasib.bank.repository.RoleRepository;
import com.hasib.bank.repository.UserRepository;
import com.hasib.bank.security.JwtGenerator;
import com.hasib.bank.util.EmailService;
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
import java.util.Collections;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth/")
public class AuthController {
    private final AuthenticationManager authenticationManager;
    private final UserRepository userRepository;
    private final AddressRepository addressRepository;
    private final RoleRepository roleRepository;
    private final OtpVerificationRepository otpVerificationRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtGenerator jwtGenerator;
    private final EmailService emailService;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, UserRepository userRepository, AddressRepository addressRepository, RoleRepository roleRepository, OtpVerificationRepository otpVerificationRepository, PasswordEncoder passwordEncoder, JwtGenerator jwtGenerator, EmailService emailService) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.addressRepository = addressRepository;
        this.roleRepository = roleRepository;
        this.otpVerificationRepository = otpVerificationRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
        this.emailService = emailService;
    }

    @PostMapping("register")
    public ResponseEntity<RegisterResponseDto> register(@RequestBody RegisterDto registerDto) {
        RegisterResponseDto responseDto = new RegisterResponseDto();
        if (userRepository.existsByNid(registerDto.getNid())) {
            responseDto.setMessage("Duplicate NID found!");
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByEmail(registerDto.getEmail())) {
            responseDto.setMessage("Duplicate Email found!");
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            responseDto.setMessage("Username is taken!");
            return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
        }

        Role roles = roleRepository.findByName("USER").get();

        Address savedAddress = addressRepository.save(registerDto.getAddress());
        UserEntity user = UserEntity.builder()
                .name(registerDto.getName())
                .nid(registerDto.getNid())
                .address(savedAddress)
                .email(registerDto.getEmail())
                .username(registerDto.getUsername())
                .password(passwordEncoder.encode((registerDto.getPassword())))
                .roles(Collections.singletonList(roles))
                .build();

        UserEntity savedUser = userRepository.save(user);

        try {
            OtpVerification otpVerification = OtpVerification.builder()
                    .userId(savedUser.getId())
                    .otp(emailService.sendOtpEmail(savedUser.getEmail()))
                    .expirationTime(LocalDateTime.now().plus(Duration.ofMinutes(3)))
                    .build();
            otpVerificationRepository.save(otpVerification);
        } catch (Exception e) {
            e.printStackTrace();
            addressRepository.delete(savedAddress);
            userRepository.delete(savedUser);
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
        System.out.println(verifyDto);
        if (!userRepository.existsById(verifyDto.getUserId())) {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }

        Optional<OtpVerification> otpVerification = otpVerificationRepository.findOtpVerificationByUserId(verifyDto.getUserId());
        if (otpVerification.isPresent()) {
            System.out.println(LocalDateTime.now().isBefore(otpVerification.get().getExpirationTime()));
            if (!LocalDateTime.now().isBefore(otpVerification.get().getExpirationTime())) {
                return new ResponseEntity<>("OTP timeout!", HttpStatus.BAD_REQUEST);
            }
            if (otpVerification.get().getOtp() == verifyDto.getOtp()) {
                otpVerificationRepository.delete(otpVerification.get());
                return ResponseEntity.ok("Verification successful");
            }
        }

        return new ResponseEntity<>("Verification failed", HttpStatus.BAD_REQUEST);
    }

    @GetMapping("resend-otp")
    public ResponseEntity<String> resendOtp(@RequestParam int userId) {
        Optional<UserEntity> user = userRepository.findById(userId);
        Optional<OtpVerification> otpVerification = otpVerificationRepository.findOtpVerificationByUserId(userId);
        if (user.isPresent() && otpVerification.isPresent()) {
            otpVerification.get().setOtp(emailService.sendOtpEmail(user.get().getEmail()));
            otpVerification.get().setExpirationTime(LocalDateTime.now().plus(Duration.ofMinutes(3)));
            otpVerificationRepository.save(otpVerification.get());
            return ResponseEntity.ok("OTP resent");
        }

        return new ResponseEntity<>("OTP resend failed", HttpStatus.BAD_REQUEST);
    }
}
