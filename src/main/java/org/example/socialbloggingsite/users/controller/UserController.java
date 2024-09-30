package org.example.socialbloggingsite.users.controller;

import org.example.socialbloggingsite.users.dtos.UserDtoUpdate;
import org.example.socialbloggingsite.users.model.User;
import org.example.socialbloggingsite.users.service.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth/users")
@RestController
public class UserController {
    private final UserServiceImpl userService;
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<?> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }

//    @PutMapping("/me")
//    public ResponseEntity<?> updateUser(@RequestBody UserDtoUpdate updateUserDto) throws Exception {
//        User updateUser = userService.updateUser(updateUserDto);
//        return ResponseEntity.ok(updateUser);
//    }
}
