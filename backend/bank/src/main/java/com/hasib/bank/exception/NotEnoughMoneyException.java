package com.hasib.bank.exception;

import java.io.Serial;

public class NotEnoughMoneyException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 4;

    public NotEnoughMoneyException(String message) {
        super(message);
    }
}
