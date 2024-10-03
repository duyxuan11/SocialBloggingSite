package org.example.socialbloggingsite.users.services.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.valves.rewrite.InternalRewriteMap;
import org.example.socialbloggingsite.exceptions.customs.CustomRunTimeException;
import org.example.socialbloggingsite.exceptions.customs.ErrorCode;
import org.example.socialbloggingsite.users.dto.UserResponse;
import org.example.socialbloggingsite.users.dto.UserUpdateDto;
import org.example.socialbloggingsite.users.models.UserEntity;
import org.example.socialbloggingsite.users.repositories.UserRepository;
import org.example.socialbloggingsite.users.services.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;
    ModelMapper modelMapper;

    @Override
    public UserResponse getUser(Long userId){
        var user = userRepository.findById(userId).orElseThrow(()->new CustomRunTimeException(ErrorCode.USER_NOT_FOUND));
        return modelMapper.map(user, UserResponse.class);
    }

    @Override
    public void updateUser(UserUpdateDto input, Long userId){
        var oldUser = userRepository.findById(userId).orElseThrow(()->new CustomRunTimeException(ErrorCode.USER_NOT_FOUND));
        var newUser = modelMapper.map(input, UserEntity.class);
        modelMapper.map(oldUser, newUser);
        String newPassword = input.getPassword() == null ? oldUser.getPassword() : passwordEncoder.encode(oldUser.getPassword());
        newUser.setPassword(newPassword);
        userRepository.save(newUser);
    }

}
