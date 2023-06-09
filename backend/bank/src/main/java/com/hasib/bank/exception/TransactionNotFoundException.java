package com.hasib.bank.exception;

import java.io.Serial;

public class TransactionNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 3;

    public TransactionNotFoundException(String message) {
        super(message);
    }
}
