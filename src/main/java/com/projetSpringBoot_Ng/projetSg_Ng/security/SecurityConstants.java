package com.projetSpringBoot_Ng.projetSg_Ng.security;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.util.Base64;

public class SecurityConstants {
    private static final java.security.Key key = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    public static final String JWT_SECRET = Base64.getEncoder().encodeToString(key.getEncoded());
    public static final long JWT_EXPIRATION = 864000000; // 10 days
}
