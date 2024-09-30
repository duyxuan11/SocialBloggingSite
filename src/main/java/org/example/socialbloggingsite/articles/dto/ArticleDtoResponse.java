package org.example.socialbloggingsite.articles.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.socialbloggingsite.categories.dto.CategoryDtoResponse;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleDtoResponse {
    int id;
    String title;
    String content;
    Date createdAt;
    String imageUrl;
    UserDtoResponse user;
    CategoryDtoResponse category;
}
