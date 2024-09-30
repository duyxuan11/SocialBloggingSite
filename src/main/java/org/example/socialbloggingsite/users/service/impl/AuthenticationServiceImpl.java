package org.example.socialbloggingsite.users.service.impl;

import jakarta.servlet.http.HttpServletRequest;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.socialbloggingsite.users.dtos.UserDtoLogin;
import org.example.socialbloggingsite.users.service.AuthenticationService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.example.socialbloggingsite.users.repositories.UserRepository;
import org.example.socialbloggingsite.users.dtos.UserDtoCreate;
import org.example.socialbloggingsite.users.model.User;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthenticationServiceImpl implements AuthenticationService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    AuthenticationManager authenticationManager;


//    public User signup(UserDtoLogin input) {
//        System.out.println(input.toString());
//        var user = new User()
//                .setFullName(input.getFullName())
//                .setEmail(input.getEmail())
//                .setPassword(passwordEncoder.encode(input.getPassword()));
//        return userRepository.save(user);
//    }

    @Override
    public User authenticate(UserDtoLogin input) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        input.getUserName(),
                        input.getPassword()
                )
        );
        return userRepository.findByUsername(input.getUserName()).orElseThrow(() -> new UsernameNotFoundException("User Or Password not found"));
    }

    @Override
    public String getJwtFromRequest(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7);
        }
        return null;
    }
}
