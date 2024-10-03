package org.example.socialbloggingsite.users.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.socialbloggingsite.users.dto.TokenRefreshDto;
import org.example.socialbloggingsite.users.dto.UserCreateDto;
import org.example.socialbloggingsite.users.dto.UserLoginDto;
import org.example.socialbloggingsite.users.dto.UserLoginResponse;
import org.example.socialbloggingsite.users.services.AuthenticationService;
import org.example.socialbloggingsite.users.services.JwtTokenBlackListService;
import org.example.socialbloggingsite.users.services.impl.JwtTokenBlackListServiceImpl;
import org.example.socialbloggingsite.utils.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.socialbloggingsite.users.services.impl.AuthenticationServiceImpl;

@RequestMapping("/api/auth")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class AuthenticationController {
    JwtUtils jwtUtils;
    AuthenticationService authenticationService;
    JwtTokenBlackListService jwtTokenBlackListService;

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody @Valid UserCreateDto userCreateDto){
        var registerUser = authenticationService.signUp(userCreateDto);
        return ResponseEntity.ok(registerUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> authenticate(@RequestBody @Valid UserLoginDto loginUserDto){
            var loginResponse = authenticationService.authenticate(loginUserDto);
            return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<?> refreshToken(@RequestBody @Valid TokenRefreshDto request){
//        String oldToken = authenticationService.getJwtFromRequest(request);
//        return ResponseEntity.ok(authenticationService.refreshToken(oldToken));
        var response = authenticationService.refreshToken(request);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request){
        authenticationService.logOut(request);
        return ResponseEntity.ok("Logout successful");
    }


}
