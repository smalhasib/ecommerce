package com.hasib.organization.exception;

import java.io.Serial;

public class OtpVerificationNotFoundException extends RuntimeException {
    @Serial
    private static final long serialVersionUID = 2;

    public OtpVerificationNotFoundException(String message) {
        super(message);
    }
}
