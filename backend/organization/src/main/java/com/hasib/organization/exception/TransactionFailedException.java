package com.hasib.organization.exception;

import java.io.Serial;

public class TransactionFailedException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 4;

    public TransactionFailedException(String message) {
        super(message);
    }
}
