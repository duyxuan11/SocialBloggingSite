package org.example.socialbloggingsite.users.services;

public interface JwtTokenBlackListService {
    void addBlacklist(String token);
    boolean isBlacklisted(String token);
}
