package org.example.socialbloggingsite.users.service;

public interface JwtTokenBlackListService {
    void addBlacklist(String token);
    boolean isBlacklisted(String token);
}
