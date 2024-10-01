package org.example.socialbloggingsite.articles.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticlesCreateDto {
    String title;
    String content;
    String imgUrl;
    Integer categoryId;
    Integer userId;
}
