package org.example.socialbloggingsite.articles.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GetArticleDto {
    String title;
    String content;
    Date createdAt;
    String category;
    Integer user_id;

}
