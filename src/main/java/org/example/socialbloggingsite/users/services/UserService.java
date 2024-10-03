package org.example.socialbloggingsite.users.services;

import org.example.socialbloggingsite.users.dto.UserResponse;
import org.example.socialbloggingsite.users.dto.UserUpdateDto;

public interface UserService {
    UserResponse getUser(Long userId);
    void updateUser(UserUpdateDto request, Long userId);
}
