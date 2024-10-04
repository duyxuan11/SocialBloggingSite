package org.example.socialbloggingsite.favorites.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.socialbloggingsite.articles.dto.ArticleResponse;
import org.example.socialbloggingsite.articles.dto.UserArticleResponse;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FavoriteListResponse {
    ArticleResponse article;
    List<UserArticleResponse> user;
}
