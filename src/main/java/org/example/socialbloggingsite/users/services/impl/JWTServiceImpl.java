package org.example.socialbloggingsite.users.services.impl;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.socialbloggingsite.users.services.JWTService;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Getter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class JWTServiceImpl implements JWTService {

}
