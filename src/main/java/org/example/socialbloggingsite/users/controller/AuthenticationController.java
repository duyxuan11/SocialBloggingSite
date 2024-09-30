package org.example.socialbloggingsite.users.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.example.socialbloggingsite.users.dtos.UserDtoLogin;
import org.example.socialbloggingsite.users.dtos.UserDtoLoginResponse;
import org.example.socialbloggingsite.users.service.impl.JwtTokenBlackListServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.socialbloggingsite.users.dtos.UserDtoCreate;
import org.example.socialbloggingsite.users.model.User;
import org.example.socialbloggingsite.users.service.impl.AuthenticationServiceImpl;
import org.example.socialbloggingsite.users.service.impl.JWTServiceImpl;

@RequestMapping("/api/auth")
@RestController
public class AuthenticationController {
    private final JWTServiceImpl jwtService;
    private final AuthenticationServiceImpl authenticationService;

    @Autowired
    private JwtTokenBlackListServiceImpl jwtTokenBlackListService;

    public AuthenticationController(JWTServiceImpl jwtService, AuthenticationServiceImpl authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

//    @GetMapping("/hello")
//    public String hello() {
//        return "Hello World";
//    }

//    @PostMapping("/signup")
//    public ResponseEntity<?> register(@RequestBody UserDtoLogin registerUserDto, Model model){
//        System.out.println(registerUserDto.toString());
//        User registerUser = authenticationService.signup(registerUserDto);
//        return ResponseEntity.ok(registerUser);
//    }

    @PostMapping("/login")
    public ResponseEntity<UserDtoLoginResponse> authenticate(@RequestBody UserDtoLogin loginUserDto){
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String token = jwtService.generateToken(authenticatedUser);
        UserDtoLoginResponse loginResponse = UserDtoLoginResponse.builder()
                        .token(token)
                                .expiresIn(jwtService.getExpirationTime())
                                        .build();
        return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request){
//        String refreshToken = request.getHeader("refresh-token");
//        SecurityContextHolder.clearContext();
//        return ResponseEntity.ok(new LoginResponse());
        String token = authenticationService.getJwtFromRequest(request);
        jwtTokenBlackListService.addBlacklist(token);
        return ResponseEntity.ok("Logout successful");
    }


}
