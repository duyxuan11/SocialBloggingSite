package org.example.socialbloggingsite.users.services;

import org.example.socialbloggingsite.users.models.RefreshTokenEntity;

public interface RefreshTokenService {
//    RefreshTokenEntity verifyExpiration(RefreshTokenEntity refreshToken);
    RefreshTokenEntity createRefreshToken(int userId);
    boolean isRefreshTokenExpired(RefreshTokenEntity refreshToken);
    int deleteByUserUsername(String username);
}
