package org.example.socialbloggingsite.users.dto;

import jakarta.validation.constraints.NotBlank;
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
    @NotBlank(message = "username must not be null")
    private String password;

    @NotNull(message = "password is required")
    @NotBlank(message = "password must not be null")
    private String userName;
}
