package org.example.socialbloggingsite.comments.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.socialbloggingsite.articles.dto.UserArticleResponse;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CommentResponse {
    int id;
    String comment;
    UserArticleResponse user;
    ArticleCommentResponse article;
}
