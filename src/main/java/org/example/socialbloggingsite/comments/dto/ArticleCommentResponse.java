package org.example.socialbloggingsite.comments.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.socialbloggingsite.articles.dto.UserArticleResponse;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleCommentResponse {
    int id;
    String title;
    String content;
    Date createdAt;
    int totalFavorites;
    UserArticleResponse user;
}
