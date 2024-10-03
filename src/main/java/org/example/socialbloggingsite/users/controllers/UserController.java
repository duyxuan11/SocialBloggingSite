package org.example.socialbloggingsite.users.controllers;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.socialbloggingsite.users.dto.UserResponse;
import org.example.socialbloggingsite.users.dto.UserUpdateDto;
import org.example.socialbloggingsite.users.services.UserService;
import org.example.socialbloggingsite.utils.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/users")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    UserService userService;
    JwtUtils jwtUtils;


    @GetMapping("{userId}")
    @Transactional
    public ResponseEntity<?> getUser(@PathVariable Long userId) {
        if(userId == null) {
            return ResponseEntity.badRequest().body("No userId provided. Try again?");
        }
        UserResponse currentUser = userService.getUser(userId);
        return ResponseEntity.ok(currentUser);
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @RequestBody UserUpdateDto request){
        if(userId == null) {
            return ResponseEntity.badRequest().body("No userId provided. Try again?");
        }
        userService.updateUser(request, userId);
        return ResponseEntity.ok("Updated user successfully.");
    }
}
