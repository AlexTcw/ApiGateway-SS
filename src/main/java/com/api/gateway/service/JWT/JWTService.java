package com.api.gateway.service.JWT;

import org.springframework.security.core.userdetails.UserDetails;

import javax.crypto.SecretKey;
import java.util.Map;

public interface JWTService {
    String getToken(UserDetails user);

    String getToken(Map<String, Object> extraClaim, UserDetails user);

    SecretKey getKey();

    String getUserNameFromToken(String token);

    boolean isTokenValid(String token, UserDetails userDetails);
}
