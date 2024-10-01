package org.example.socialbloggingsite.users.services;

import jakarta.servlet.http.HttpServletRequest;
import org.example.socialbloggingsite.users.dto.*;

public interface AuthenticationService {
    UserLoginResponse authenticate(UserLoginDto input);
    String getJwtFromRequest(HttpServletRequest request);
    UserResponse signUp(UserCreateDto input);
    void logOut(HttpServletRequest req);
    UserLoginResponse refreshToken(TokenRefreshDto request);
}
