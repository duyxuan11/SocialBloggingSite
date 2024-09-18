package org.example.socialbloggingsite.user.service;

import org.example.socialbloggingsite.user.Repositories.UserRepository;
import org.example.socialbloggingsite.user.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

}
