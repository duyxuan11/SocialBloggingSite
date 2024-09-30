package org.example.socialbloggingsite.users.dtos;

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
public class UserDtoResponse {
    String username;
    String firstName;
    String lastName;
    String email;
    Date birthDate;
    String imgUrl;
    Role role;
    Gender gender;
    String createdBy;
}
