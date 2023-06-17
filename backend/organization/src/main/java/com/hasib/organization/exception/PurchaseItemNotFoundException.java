package com.hasib.organization.exception;

import java.io.Serial;

public class PurchaseItemNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 6;

    public PurchaseItemNotFoundException(String message) {
        super(message);
    }
}
