package org.example.socialbloggingsite.users.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.socialbloggingsite.users.dto.UserLoginDto;
import org.example.socialbloggingsite.users.dto.UserLoginResponse;
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
    AuthenticationServiceImpl authenticationService;
    JwtTokenBlackListServiceImpl jwtTokenBlackListService;

//    @PostMapping("/signup")
//    public ResponseEntity<?> register(@RequestBody UserDtoCreate userDtoCreate, Model model){
//        var registerUser = authenticationService.signUp(userDtoCreate);
//        return ResponseEntity.ok(registerUser);
//    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponse> authenticate(@RequestBody @Valid UserLoginDto loginUserDto){
            var loginResponse = authenticationService.authenticate(loginUserDto);
            return ResponseEntity.ok(loginResponse);
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logout(HttpServletRequest request){
        authenticationService.logOut(request);
        String token = authenticationService.getJwtFromRequest(request);
        jwtTokenBlackListService.addBlacklist(token);
        return ResponseEntity.ok("Logout successful");
    }


}
