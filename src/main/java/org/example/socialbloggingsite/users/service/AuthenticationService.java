package org.example.socialbloggingsite.users.service;

import jakarta.servlet.http.HttpServletRequest;
import org.example.socialbloggingsite.users.dtos.UserDtoCreate;
import org.example.socialbloggingsite.users.dtos.UserDtoLogin;
import org.example.socialbloggingsite.users.model.User;

public interface AuthenticationService {
    User authenticate(UserDtoLogin input);
    String getJwtFromRequest(HttpServletRequest request);
}
