package org.example.socialbloggingsite.users.dtos;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserDtoLoginResponse {
    String token;
    long expiresIn;
}
