package org.example.socialbloggingsite.user.service;

import org.example.socialbloggingsite.user.Repositories.UserRepository;
import org.example.socialbloggingsite.user.dtos.UpdateUserDto;
import org.example.socialbloggingsite.user.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User updateUser(UpdateUserDto input) throws Exception {
        try {
            User user = userRepository.findByEmail(input.getEmail()).orElseThrow(() -> new Exception("Email User Not Correct"));
            String fullName = input.getFullName() == null ? user.getFullName() : input.getFullName();
            String email = input.getEmail() == null ? user.getEmail() : input.getEmail();
            String password = input.getPassword() == null ? user.getPassword() : input.getPassword();

            user.setFullName(fullName);
            user.setEmail(email);
            user.setPassword(password);
            return userRepository.save(user);
        }catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

}
