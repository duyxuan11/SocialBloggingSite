package org.example.socialbloggingsite.users.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.socialbloggingsite.exceptions.customs.CustomRunTimeException;
import org.example.socialbloggingsite.exceptions.customs.ErrorCode;
import org.example.socialbloggingsite.users.dto.UserResponse;
import org.example.socialbloggingsite.users.dto.UserUpdateDto;
import org.example.socialbloggingsite.users.services.UserService;
import org.example.socialbloggingsite.utils.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/api/users")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;
    JwtUtils jwtUtils;


    @GetMapping(value = {"/","/{userId}"})
    @Transactional
    public ResponseEntity<?> getUser(@PathVariable Optional<Long> userId) {
        if(userId.isEmpty()) {
            throw new CustomRunTimeException(ErrorCode.USER_ID_INVALID);
        }
        UserResponse currentUser = userService.getUser(userId.get());
        return ResponseEntity.ok(currentUser);
    }

    @PutMapping(value = {"/","/{userId}"})
    public ResponseEntity<?> updateUser(@PathVariable Optional<Long> userId, @RequestBody UserUpdateDto request){
        if(userId.isEmpty()) {
            throw new CustomRunTimeException(ErrorCode.USER_ID_INVALID);
        }
        userService.updateUser(request, userId.get());
        return ResponseEntity.ok("Updated user successfully.");
    }
}
