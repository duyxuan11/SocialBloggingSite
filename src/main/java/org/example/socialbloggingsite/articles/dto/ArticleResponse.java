package org.example.socialbloggingsite.articles.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.socialbloggingsite.comments.dto.CommentArticleResponse;

import java.util.Date;
import java.util.List;

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
    List<CommentArticleResponse> comments;
}
