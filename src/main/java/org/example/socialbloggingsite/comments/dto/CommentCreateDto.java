package org.example.socialbloggingsite.comments.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentCreateDto {
    @NotNull(message = "comment must not be null")
    @NotBlank(message = "comment must not be null")
    String comment;
}
