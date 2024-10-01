package org.example.socialbloggingsite.users.services;

import jakarta.servlet.http.HttpServletRequest;
import org.example.socialbloggingsite.users.dto.UserLoginDto;
import org.example.socialbloggingsite.users.dto.UserLoginResponse;

public interface AuthenticationService {
    UserLoginResponse authenticate(UserLoginDto input);
    String getJwtFromRequest(HttpServletRequest request);
//    User signUp(UserDtoCreate input);
    void logOut(HttpServletRequest req);
}
