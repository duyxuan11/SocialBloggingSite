package org.example.socialbloggingsite.users.controllers;

import org.example.socialbloggingsite.users.dto.UserResponse;
import org.example.socialbloggingsite.users.services.impl.UserServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/auth/users")
@RestController
public class UserController {
    private final UserServiceImpl userService;
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    @Transactional
    public ResponseEntity<?> getUser() {
        UserResponse currentUser = userService.getUser();
        return ResponseEntity.ok(currentUser);
    }

//    @PutMapping("/me")
//    public ResponseEntity<?> updateUser(@RequestBody UserDtoUpdate updateUserDto) throws Exception {
//        User updateUser = userService.updateUser(updateUserDto);
//        return ResponseEntity.ok(updateUser);
//    }
}
