package org.example.socialbloggingsite.users.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.socialbloggingsite.utils.constants.Gender;
import org.example.socialbloggingsite.utils.constants.Role;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserUpdateDto {
    String username;
    String email;
    String firstName;
    String lastName;
    String password;
    Date birthDay;
    Gender gender;
}
