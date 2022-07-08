package com.zurum.lanefinance.constants;

public class SecurityConstants {

    public static String SECRET = "secret should be inside an env and encrypted";
    public static final long EXPIRATION_TIME = 3_200_000; // 60 mins
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String SIGN_UP_URL = "/auth/register";
}
