package com.hasib.organization.exception;

import java.io.Serial;

public class TransactionNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 5;

    public TransactionNotFoundException(String message) {
        super(message);
    }
}
