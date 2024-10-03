package org.example.socialbloggingsite.articles.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.socialbloggingsite.comments.dtos.CommentArticleResponse;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleResponse {
    int id;
    String title;
    String content;
    Date createdAt;
    int totalFavorites;
    UserArticleResponse user;
    CommentArticleResponse comments;
}
