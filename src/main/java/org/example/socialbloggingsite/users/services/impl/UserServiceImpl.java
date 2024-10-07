package org.example.socialbloggingsite.users.services.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.socialbloggingsite.exceptions.customs.CustomRunTimeException;
import org.example.socialbloggingsite.exceptions.customs.ErrorCode;
import org.example.socialbloggingsite.users.dto.UserResponse;
import org.example.socialbloggingsite.users.dto.UserUpdateDto;
import org.example.socialbloggingsite.users.repositories.UserRepository;
import org.example.socialbloggingsite.users.services.UserService;
import org.example.socialbloggingsite.utils.constants.Gender;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

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
        String username = input.getUsername() == null ? oldUser.getUsername() : input.getUsername();
        String newPassword = input.getPassword() == null ? oldUser.getPassword() : passwordEncoder.encode(input.getPassword());
        String email = input.getEmail() == null ? oldUser.getEmail() : input.getEmail();
        String firstName = input.getFirstName() == null ? oldUser.getFirstName() : input.getFirstName();
        String lastName = input.getLastName() == null ? oldUser.getLastName() : input.getLastName();
        Date birthDay = input.getBirthDay()== null ? oldUser.getBirthDay() : input.getBirthDay();
        Gender gender = input.getGender() == null ? oldUser.getGender() : input.getGender();
        oldUser.setPassword(newPassword);
        oldUser.setEmail(email);
        oldUser.setFirstName(firstName);
        oldUser.setLastName(lastName);
        oldUser.setBirthDay(birthDay);
        oldUser.setGender(gender);
        oldUser.setUsername(username);
        userRepository.save(oldUser);
    }

}
