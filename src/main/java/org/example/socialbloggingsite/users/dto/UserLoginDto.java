package org.example.socialbloggingsite.users.dto;

import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserLoginDto {
    @NotNull(message = "username is required")
//    @NotBlank(message = "username is required")
    private String username;
    @NotNull(message = "password is required")
    private String password;
}
