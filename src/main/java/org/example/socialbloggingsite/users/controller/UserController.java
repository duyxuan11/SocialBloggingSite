package org.example.socialbloggingsite.user.controller;

import org.example.socialbloggingsite.user.dtos.UpdateUserDto;
import org.example.socialbloggingsite.user.model.User;
import org.example.socialbloggingsite.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth/users")
@RestController
public class UserController {
    private final UserService userService;
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<?> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }

    @PutMapping("/me")
    public ResponseEntity<?> updateUser(@RequestBody UpdateUserDto updateUserDto) throws Exception {
        User updateUser = userService.updateUser(updateUserDto);
        return ResponseEntity.ok(updateUser);
    }
}
