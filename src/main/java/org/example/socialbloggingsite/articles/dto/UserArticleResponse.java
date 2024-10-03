package org.example.socialbloggingsite.articles.dto;

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
public class UserArticleResponse {
    int id;
    String username;
    String firstName;
    String lastName;
    String email;
    Date birthday;
    Role role;
    Gender gender;
}
