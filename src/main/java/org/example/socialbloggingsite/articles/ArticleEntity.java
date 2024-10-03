package org.example.socialbloggingsite.articles;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.socialbloggingsite.comments.CommentEntity;
import org.example.socialbloggingsite.favorites.FavoriteEntity;
import org.example.socialbloggingsite.users.models.UserEntity;
import org.example.socialbloggingsite.utils.BaseEntity;

import java.util.Set;

@Entity(name = "articles")
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ArticleEntity extends BaseEntity {
    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    @NotNull(message = "title must not be null")
    String title;

    @Lob
    @NotNull(message = "content must not be null")
    @Column(nullable = false, columnDefinition = "LONGTEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @JsonManagedReference
    @NotNull(message = "user_id must not be null")
    UserEntity user;

    @OneToMany(mappedBy = "article",fetch = FetchType.LAZY)
    @JsonManagedReference
    Set<CommentEntity> comments;

    @OneToMany(mappedBy = "article",fetch = FetchType.LAZY)
    @JsonManagedReference
    Set<FavoriteEntity> favorites;
}
