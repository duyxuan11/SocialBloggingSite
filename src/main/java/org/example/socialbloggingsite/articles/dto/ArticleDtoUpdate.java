package org.example.socialbloggingsite.articles.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleDtoUpdate {
    String title;
    String content;
    String published;
    int categoryId;
    int userId;


}
