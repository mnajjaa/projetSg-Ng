package com.projetSpringBoot_Ng.projetSg_Ng.security;

public class SecurityConstants {
    public static final long JWT_EXPIRATION = 864000000; // 10 days
    public static final byte[] JWT_SECRET = "MySecret".getBytes();
}
