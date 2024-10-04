package org.example.socialbloggingsite.articles.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticlesCreateDto {
    @NotNull(message = "title must not be null")
    @NotBlank(message = "title must not be null")
    String title;

    @NotNull(message = "content must not be null")
    @NotBlank(message = "content must not be null")
    String content;
}
