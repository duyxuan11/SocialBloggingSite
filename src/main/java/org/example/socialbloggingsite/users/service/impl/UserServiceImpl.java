package org.example.socialbloggingsite.users.service.impl;

import org.example.socialbloggingsite.users.Repositories.UserRepository;
import org.example.socialbloggingsite.users.dtos.UserDtoUpdate;
import org.example.socialbloggingsite.users.model.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public User updateUser(UserDtoUpdate input) throws Exception {
        try {
            User user = userRepository.findByEmail(input.getEmail()).orElseThrow(() -> new Exception("Email User Not Correct"));
            String fullName = input.getFullName() == null ? user.getFullName() : input.getFullName();
            String email = input.getEmail() == null ? user.getEmail() : input.getEmail();
            String password = passwordEncoder.encode(input.getPassword()) == null ? user.getPassword() : input.getPassword();

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
