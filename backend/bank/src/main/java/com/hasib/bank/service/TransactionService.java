package com.hasib.bank.service;

import com.hasib.bank.dto.TransactionRequestDto;
import com.hasib.bank.dto.TransactionResponseDto;
import com.hasib.bank.dto.TransactionsResponseDto;

public interface TransactionService {
    TransactionResponseDto createTransaction(TransactionRequestDto transactionRequestDto);

    TransactionsResponseDto getAllTransactions(int pageNumber, int pageSize);

    TransactionResponseDto getTransactionById(int id);

    TransactionsResponseDto getAllTransactionsBySenderId(int senderId, int pageNumber, int pageSize);

    TransactionsResponseDto getAllTransactionsByReceiverId(int receiverId, int pageNumber, int pageSize);

    void deleteTransaction(int id);
}
