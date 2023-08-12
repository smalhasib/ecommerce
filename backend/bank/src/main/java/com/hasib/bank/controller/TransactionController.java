package com.hasib.bank.controller;

import com.hasib.bank.dto.*;
import com.hasib.bank.exception.NotEnoughMoneyException;
import com.hasib.bank.model.OtpVerification;
import com.hasib.bank.service.OtpVerificationService;
import com.hasib.bank.service.TransactionService;
import com.hasib.bank.service.UserService;
import com.hasib.bank.util.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Map;

@RestController
@RequestMapping("/api/transaction/")
public class TransactionController {

    private final TransactionService transactionService;
    private final UserService userService;
    private final OtpVerificationService otpVerificationService;
    private final EmailService emailService;

    @Autowired
    public TransactionController(TransactionService transactionService, UserService userService, OtpVerificationService otpVerificationService, EmailService emailService) {
        this.transactionService = transactionService;
        this.userService = userService;
        this.otpVerificationService = otpVerificationService;
        this.emailService = emailService;
    }

    @GetMapping("/")
    public ResponseEntity<TransactionsResponseDto> getTransactions(
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return ResponseEntity.ok(transactionService.getAllTransactions(pageNumber, pageSize));
    }

    @GetMapping("{senderId}/sent")
    public ResponseEntity<TransactionsResponseDto> getTransactionsBySenderId(
            @PathVariable int senderId,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return ResponseEntity.ok(transactionService.getAllTransactionsBySenderId(senderId, pageNumber, pageSize));
    }

    @GetMapping("{receiverId}/received")
    public ResponseEntity<TransactionsResponseDto> getTransactionsByReceiverId(
            @PathVariable int receiverId,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return ResponseEntity.ok(transactionService.getAllTransactionsByReceiverId(receiverId, pageNumber, pageSize));
    }

    @GetMapping("{userId}/user")
    public ResponseEntity<TransactionsResponseDto> getTransactionsByUserId(
            @PathVariable int userId,
            @RequestParam(value = "pageNumber", defaultValue = "0", required = false) int pageNumber,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ) {
        return ResponseEntity.ok(transactionService.getAllTransactionsByUserId(userId, pageNumber, pageSize));
    }

    @GetMapping("{id}")
    public ResponseEntity<TransactionResponseDto> getTransaction(@PathVariable int id) {
        return ResponseEntity.ok(transactionService.getTransactionById(id));
    }

    @DeleteMapping("{id}/delete")
    public ResponseEntity<String> deleteTransaction(@PathVariable("id") int id) {
        transactionService.deleteTransaction(id);
        return ResponseEntity.ok("Transaction deleted successfully ");
    }

    @PostMapping("transfer")
    public ResponseEntity<?> transferMoney(@RequestBody TransactionRequestDto transactionRequestDto) {
        try {
            TransactionResponseDto responseDto = transactionService.createTransaction(transactionRequestDto);
            return ResponseEntity.ok(responseDto);
        } catch (NotEnoughMoneyException e) {
            return new ResponseEntity<>(e, HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody Map<String, Integer> body) {
        long accountNumber = body.get("accountNumber");
        UserDto user = userService.getUserByAccountNumber(accountNumber);
        try {
            otpVerificationService.createAccountVerificationOtp(accountNumber, emailService.sendOtpEmail(user.getEmail()));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Otp send failed", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok("OTP sent");
    }

    @PostMapping("verify")
    public ResponseEntity<String> verify(@RequestBody VerifyDto verifyDto) {
        if (!userService.userExistsByAccountNumber(verifyDto.getAccountNumber())) {
            return new ResponseEntity<>("Account not found", HttpStatus.BAD_REQUEST);
        }

        OtpVerification otpVerification = otpVerificationService.getOtpVerificationByAccountNumber(verifyDto.getAccountNumber());

        if (!LocalDateTime.now().isBefore(otpVerification.getExpirationTime())) {
            return new ResponseEntity<>("OTP timeout!", HttpStatus.BAD_REQUEST);
        }

        if (otpVerification.getOtp() == verifyDto.getOtp()) {
            otpVerificationService.deleteOtp(otpVerification.getId());
            return ResponseEntity.ok("Verification successful");
        }

        return new ResponseEntity<>("Verification failed", HttpStatus.BAD_REQUEST);
    }
}
