package com.hasib.organization.security;

import java.time.Duration;

public class SecurityConstants {
    public static final long JWT_EXPIRATION = Duration.ofHours(1).toMillis();
    public static final String JWT_SECRET = "mita";
}
