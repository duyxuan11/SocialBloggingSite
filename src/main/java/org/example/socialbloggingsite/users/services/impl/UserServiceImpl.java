package org.example.socialbloggingsite.users.services.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.socialbloggingsite.users.dto.UserResponse;
import org.example.socialbloggingsite.users.models.User;
import org.example.socialbloggingsite.users.repositories.UserRepository;
import org.example.socialbloggingsite.users.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    ModelMapper modelMapper;

    @Override
    public UserResponse getUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var username = (User) authentication.getPrincipal();
        var user = userRepository.findByUsername(username.getUsername()).orElseThrow(()->new RuntimeException("User not found"));
        return modelMapper.map(user, UserResponse.class);
    }


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
