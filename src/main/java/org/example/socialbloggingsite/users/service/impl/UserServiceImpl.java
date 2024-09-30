package org.example.socialbloggingsite.users.service.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.socialbloggingsite.users.repositories.UserRepository;
import org.example.socialbloggingsite.users.service.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;


//    public User updateUser(UserDtoUpdate input) throws Exception {
//        try {
//            User user = userRepository.findByEmail(input.getEmail()).orElseThrow(() -> new Exception("Email User Not Correct"));
//            String fullName = input.getFullName() == null ? user.getFullName() : input.getFullName();
//            String email = input.getEmail() == null ? user.getEmail() : input.getEmail();
//            String password = passwordEncoder.encode(input.getPassword()) == null ? user.getPassword() : input.getPassword();
//
//            user.setFullName(fullName);
//            user.setEmail(email);
//            user.setPassword(password);
//            return userRepository.save(user);
//        }catch (Exception e) {
//            e.printStackTrace();
//            throw new Exception(e.getMessage());
//        }
//    }

}
