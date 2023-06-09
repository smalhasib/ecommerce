package com.hasib.bank.service.impl;

import com.hasib.bank.dto.TransactionRequestDto;
import com.hasib.bank.dto.TransactionResponseDto;
import com.hasib.bank.dto.TransactionsResponseDto;
import com.hasib.bank.exception.NotEnoughMoneyException;
import com.hasib.bank.exception.TransactionNotFoundException;
import com.hasib.bank.exception.UserNotFoundException;
import com.hasib.bank.mapper.TransactionMapper;
import com.hasib.bank.model.Transaction;
import com.hasib.bank.model.UserEntity;
import com.hasib.bank.repository.TransactionRepository;
import com.hasib.bank.repository.UserRepository;
import com.hasib.bank.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.hasib.bank.mapper.TransactionMapper.mapToDto;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;

    @Autowired
    public TransactionServiceImpl(TransactionRepository transactionRepository, UserRepository userRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
    }

    @Override
    public TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto) {
        UserEntity sender = userRepository.findByAccountNumber(transactionRequestDto.getSenderAccountNumber()).orElseThrow(() -> new UserNotFoundException("Invalid receiver accountNumber"));
        UserEntity receiver = userRepository.findByAccountNumber(transactionRequestDto.getReceiverAccountNumber()).orElseThrow(() -> new UserNotFoundException("Invalid receiver accountNumber"));

        if (sender.getMoney() < transactionRequestDto.getAmount()) {
            System.out.println("not enough");
            throw new NotEnoughMoneyException("Insufficient Balance");
        }

        Transaction transaction = Transaction.builder()
                .amount(transactionRequestDto.getAmount())
                .receiver(receiver)
                .sender(sender)
                .createdAt(LocalDateTime.now())
                .build();

        Transaction savedTransaction = transactionRepository.save(transaction);

        sender.setMoney(sender.getMoney() - transactionRequestDto.getAmount());
        receiver.setMoney(receiver.getMoney() + transactionRequestDto.getAmount());

        sender.getSentTransactions().add(transaction);
        receiver.getReceivedTransactions().add(transaction);
        userRepository.saveAll(List.of(sender, receiver));

        return mapToDto(savedTransaction);
    }

    @Override
    public TransactionsResponseDto getAllTransactions(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Transaction> transactionPage = transactionRepository.findAll(pageable);
        List<TransactionResponseDto> transactionDtoList = transactionPage.getContent()
                .stream()
                .map(TransactionMapper::mapToDto)
                .toList();
        return TransactionsResponseDto.builder()
                .content(transactionDtoList)
                .pageNumber(transactionPage.getNumber())
                .pageSize(transactionPage.getSize())
                .totalElements(transactionPage.getTotalElements())
                .totalPages(transactionPage.getTotalPages())
                .last(transactionPage.isLast())
                .build();
    }

    @Override
    public TransactionResponseDto getTransactionById(int id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new TransactionNotFoundException("Transaction not found"));
        return mapToDto(transaction);
    }

    @Override
    public TransactionsResponseDto getAllTransactionsBySenderId(int senderId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Transaction> transactionPage = transactionRepository.findAllBySenderId(senderId, pageable);
        List<TransactionResponseDto> transactionDtoList = transactionPage.getContent()
                .stream()
                .map(TransactionMapper::mapToDto)
                .toList();
        return TransactionsResponseDto.builder()
                .content(transactionDtoList)
                .pageNumber(transactionPage.getNumber())
                .pageSize(transactionPage.getSize())
                .totalElements(transactionPage.getTotalElements())
                .totalPages(transactionPage.getTotalPages())
                .last(transactionPage.isLast())
                .build();
    }

    @Override
    public TransactionsResponseDto getAllTransactionsByReceiverId(int receiverId, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<Transaction> transactionPage = transactionRepository.findAllByReceiverId(receiverId, pageable);
        List<TransactionResponseDto> transactionDtoList = transactionPage.getContent()
                .stream()
                .map(TransactionMapper::mapToDto)
                .toList();
        return TransactionsResponseDto.builder()
                .content(transactionDtoList)
                .pageNumber(transactionPage.getNumber())
                .pageSize(transactionPage.getSize())
                .totalElements(transactionPage.getTotalElements())
                .totalPages(transactionPage.getTotalPages())
                .last(transactionPage.isLast())
                .build();
    }

    @Override
    public void deleteTransaction(int id) {
        Transaction transaction = transactionRepository.findById(id).orElseThrow(() -> new TransactionNotFoundException("Transaction not found"));
        transactionRepository.delete(transaction);
    }
}
