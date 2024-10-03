package org.example.socialbloggingsite.follows.dto;

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
public class UserFolloweeResponse {
    int id;
    String username;
    String firstName;
    String lastName;
    String email;
    Date birthDate;
    String createdBy;
    Role role;
    Gender gender;
}
