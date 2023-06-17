package com.hasib.organization.exception;

import java.io.Serial;

public class ProductNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 3;

    public ProductNotFoundException(String message) {
        super(message);
    }
}
