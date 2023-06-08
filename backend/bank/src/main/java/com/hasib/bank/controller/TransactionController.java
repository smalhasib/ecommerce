package com.hasib.bank.controller;

import com.hasib.bank.dto.TransactionRequestDto;
import com.hasib.bank.dto.TransactionResponseDto;
import com.hasib.bank.dto.TransactionsResponseDto;
import com.hasib.bank.model.UserEntity;
import com.hasib.bank.service.OtpVerificationService;
import com.hasib.bank.service.TransactionService;
import com.hasib.bank.service.UserService;
import com.hasib.bank.util.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public ResponseEntity<TransactionResponseDto> transferMoney(@RequestBody TransactionRequestDto transactionRequestDto) {
        TransactionResponseDto responseDto = transactionService.createTransaction(transactionRequestDto);
        if (responseDto == null) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        } else {
            return ResponseEntity.ok(responseDto);
        }
    }

    @GetMapping("send-otp")
    public ResponseEntity<String> sendOtp(@RequestBody Map<String, Integer> body) {
        long accountNumber = body.get("accountNumber");
        UserEntity user = userService.getUserByAccountNumber(accountNumber);
        try {
            otpVerificationService.createAccountVerificationOtp(accountNumber, emailService.sendOtpEmail(user.getEmail()));
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Otp send failed", HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok("OTP sent");
    }
}
