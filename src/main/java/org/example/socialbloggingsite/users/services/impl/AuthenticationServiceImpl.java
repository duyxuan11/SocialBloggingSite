package org.example.socialbloggingsite.users.services.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.socialbloggingsite.users.dto.UserLoginDto;
import org.example.socialbloggingsite.users.dto.UserLoginResponse;
import org.example.socialbloggingsite.users.services.AuthenticationService;
import org.example.socialbloggingsite.utils.JwtUtils;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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


//    @Override
//    public User signUp(UserDtoCreate input) {
//        var user = new User()
//                .setFullName(input.getFullName())
//                .setEmail(input.getEmail())
//                .setPassword(passwordEncoder.encode(input.getPassword()));
//        return userRepository.save(user);
//    }

    @Override
    @Transactional
    public UserLoginResponse authenticate(UserLoginDto input) {
        var user = userRepository.findByUsername(input.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUsername(),
                        input.getPassword()
                )
        );
        String token = jwtUtils.generateToken(user);

        return UserLoginResponse.builder()
                .token(token)
                .expiresIn(jwtUtils.getExpirationTime())
                .build();
    }

    @Override
    public String getJwtFromRequest(HttpServletRequest request) {

        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            log.info(jwtUtils.extractEmail(bearerToken.substring(7)));
            return bearerToken.substring(7);
        }
        return null;
    }

    @Override
    public void logOut(HttpServletRequest request) {
        String token = getJwtFromRequest(request);
        jwtUtils.TokenInvalid(token);
    }

}
