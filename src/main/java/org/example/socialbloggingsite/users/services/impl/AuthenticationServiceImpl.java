package org.example.socialbloggingsite.users.services.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.socialbloggingsite.exceptions.customs.CustomRunTimeException;
import org.example.socialbloggingsite.exceptions.customs.ErrorCode;
import org.example.socialbloggingsite.users.dto.*;
import org.example.socialbloggingsite.users.models.UserEntity;
import org.example.socialbloggingsite.users.repositories.RefreshTokenRepository;
import org.example.socialbloggingsite.users.services.AuthenticationService;
import org.example.socialbloggingsite.users.services.RefreshTokenService;
import org.example.socialbloggingsite.utils.JwtUtils;
import org.example.socialbloggingsite.utils.constants.Role;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.example.socialbloggingsite.users.repositories.UserRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationServiceImpl implements AuthenticationService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;
    JwtUtils jwtUtils;
    RefreshTokenService refreshTokenService;
    RefreshTokenRepository refreshTokenRepository;
    private final ModelMapper modelMapper;


    @Override
    @Transactional
    public UserResponse signUp(UserCreateDto input) {
        if(userRepository.existsByUsername(input.getUsername())) {
            throw new CustomRunTimeException(ErrorCode.USER_EXISTS);
        }
        if(userRepository.existsByEmail(input.getEmail())){
            throw new CustomRunTimeException(ErrorCode.EMAIL_EXISTS);
        }
        var user = modelMapper.map(input, UserEntity.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Role.USER);
        userRepository.save(user);
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    @Transactional
    public UserLoginResponse authenticate(UserLoginDto input) {
        var user = userRepository.findByUsername(input.getUsername()).orElseThrow(() -> new CustomRunTimeException(ErrorCode.USER_NOT_FOUND));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );
        String token = jwtUtils.generateToken(user);
        //get refreshToken
        String refreshToken;
        var refreshTokenEntity = refreshTokenRepository.findByUser(user);
        if(refreshTokenEntity.isPresent() && !refreshTokenService.isRefreshTokenExpired(refreshTokenEntity.get())) {
            refreshToken = refreshTokenEntity.get().getToken();
        }else refreshToken = refreshTokenService.createRefreshToken(user.getId()).getToken();
        return UserLoginResponse.builder()
                .token(token)
                .refreshToken(refreshToken)
                .username(user.getUsername())
                .id(user.getId())
                .role(user.getRole())
                .build();
    }

    @Override
    public String getJwtFromRequest(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            log.info(jwtUtils.extractUser(bearerToken.substring(7)));
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override
    public void logOut(HttpServletRequest request) {
        String token = getJwtFromRequest(request);
        String username = jwtUtils.extractUser(token);
        refreshTokenService.deleteByUserUsername(username);
//        var tokenDel = refreshTokenRepository.findByUserName(token).orElseThrow(() -> new RuntimeException("Token not found"));
//        refreshTokenRepository.delete(tokenDel);
    }

    @Override
    public UserLoginResponse refreshToken(TokenRefreshDto request){
        var refreshTokenOpt = refreshTokenRepository.findByToken(request.getRefreshToken()).orElseThrow(() -> new CustomRunTimeException(ErrorCode.INVALID_TOKEN));

        if(refreshTokenService.isRefreshTokenExpired(refreshTokenOpt)){
            throw new CustomRunTimeException(ErrorCode.REFRESH_TOKEN_EXPIRED);
        }
        String newAccessToken = jwtUtils.generateToken(refreshTokenOpt.getUser());
        var userLoginResponse = modelMapper.map(refreshTokenOpt.getUser(), UserLoginResponse.class);
        userLoginResponse.setRefreshToken(request.getRefreshToken());
        userLoginResponse.setToken(newAccessToken);
        return userLoginResponse;
//        log.info("Refreshing token"+tokenRequest);
//        var token = refreshTokenRepository.findByToken(tokenRequest).orElseThrow(() -> new RuntimeException("Token not found"));
//        log.info("Refreshing token 1"+token.getToken());
//        var user = userRepository.findById(token.getUser().getId());
//        refreshTokenRepository.delete(token);
////        refreshTokenService.verifyExpiration(token);
//        String newToken = jwtUtils.generateToken(user.get());
//        refreshTokenService.createRefreshToken(newToken,user.get().getId());
//        return UserLoginResponse.builder()
//                .id(user.get().getId())
//                .username(user.get().getUsername())
//                .role(user.get().getRole())
//                .token(newToken)
//                .build();
    }

}
