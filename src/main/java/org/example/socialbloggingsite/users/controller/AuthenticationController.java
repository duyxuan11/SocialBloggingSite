package org.example.socialbloggingsite.user.controller;

import ch.qos.logback.core.model.Model;
import jakarta.servlet.http.HttpServletRequest;
import org.example.socialbloggingsite.user.response.LoginResponse;
import org.example.socialbloggingsite.user.service.JwtTokenBlackListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.example.socialbloggingsite.user.dtos.LoginUserDto;
import org.example.socialbloggingsite.user.dtos.RegisterUserDto;
import org.example.socialbloggingsite.user.model.User;
import org.example.socialbloggingsite.user.service.AuthenticationService;
import org.example.socialbloggingsite.user.service.JWTService;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {
    private final JWTService jwtService;
    private final AuthenticationService authenticationService;

    @Autowired
    private JwtTokenBlackListService jwtTokenBlackListService;

    public AuthenticationController(JWTService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

//    @GetMapping("/hello")
//    public String hello() {
//        return "Hello World";
//    }

    @PostMapping("/signup")
    public ResponseEntity<?> register(@RequestBody RegisterUserDto registerUserDto, Model model){
        System.out.println(registerUserDto.toString());
        User registerUser = authenticationService.signup(registerUserDto);
        return ResponseEntity.ok(registerUser);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserDto loginUserDto){
        User authenticatedUser = authenticationService.authenticate(loginUserDto);
        String token = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse().setToken(token).setExpiresIn(jwtService.getExpirationTime());
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
