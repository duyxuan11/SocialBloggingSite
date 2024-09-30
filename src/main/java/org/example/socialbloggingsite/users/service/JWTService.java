package org.example.socialbloggingsite.users.service;

import org.springframework.security.core.userdetails.UserDetails;

public interface JWTService {
    String extractEmail(String token);
    String generateToken(UserDetails userDetails);
    boolean isTokenValid(String token, UserDetails userDetails);
}
