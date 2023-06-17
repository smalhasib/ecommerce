package com.hasib.organization.exception;

import java.io.Serial;

public class PurchaseNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 7;

    public PurchaseNotFoundException(String message) {
        super(message);
    }
}
