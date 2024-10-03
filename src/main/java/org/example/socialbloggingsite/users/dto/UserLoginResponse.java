package org.example.socialbloggingsite.users.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.socialbloggingsite.utils.constants.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserLoginResponse {
    String token;
    String refreshToken;
    int id;
    String username;
    Role role;
}
