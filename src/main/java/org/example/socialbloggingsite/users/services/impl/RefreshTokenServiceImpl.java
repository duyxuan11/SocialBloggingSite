package org.example.socialbloggingsite.users.services.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.socialbloggingsite.exceptions.customs.CustomRunTimeException;
import org.example.socialbloggingsite.exceptions.customs.ErrorCode;
import org.example.socialbloggingsite.users.models.RefreshTokenEntity;
import org.example.socialbloggingsite.users.repositories.RefreshTokenRepository;
import org.example.socialbloggingsite.users.repositories.UserRepository;
import org.example.socialbloggingsite.users.services.RefreshTokenService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Date;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Slf4j
public class RefreshTokenServiceImpl implements RefreshTokenService {
    private final UserRepository userRepository;
    @Value("${security.jwt.expiration-refresh-time}")
    long refreshTime;

    @Value(("${security.jwt.expiration-time}"))
    long expiration;

    final RefreshTokenRepository refreshTokenRepository;

//    @Override
//    public RefreshTokenEntity verifyExpiration(RefreshTokenEntity refreshToken) {
//        if (refreshToken.getExpiryDate().compareTo(new Date()) < 0) {
//            refreshTokenRepository.delete(refreshToken);
//            throw new RuntimeException(refreshToken.getToken()+"Refresh token was expired. Please make a new signIn request");
//        }
//        return refreshToken;
//    }

    @Override
    public RefreshTokenEntity createRefreshToken(int userId) {
        RefreshTokenEntity refreshToken = new RefreshTokenEntity();
        var user = userRepository.findById(userId).orElseThrow(()->new CustomRunTimeException(ErrorCode.USER_NOT_FOUND));
        refreshToken.setToken(UUID.randomUUID().toString());
        refreshToken.setExpiryDate(Instant.now().plusMillis(refreshTime));
        refreshToken.setUser(user);
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public boolean isRefreshTokenExpired(RefreshTokenEntity refreshToken) {
        return refreshToken.getExpiryDate().isBefore(Instant.now());
    }

    @Override
    @Transactional
    public int deleteByUserUsername(String username){
        return refreshTokenRepository.deleteByUser(userRepository.findByUsername(username).get());
    }

}
