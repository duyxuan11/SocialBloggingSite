package org.example.socialbloggingsite.articles.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.socialbloggingsite.categories.models.Category;
import org.example.socialbloggingsite.comments.models.Comment;
import org.example.socialbloggingsite.favorites.models.Favorite;
import org.example.socialbloggingsite.users.model.User;
import org.example.socialbloggingsite.utils.base.BaseEntity;

import java.util.Set;

@Entity(name = "articles")
@Table
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Article extends BaseEntity {

    @Column(nullable = false, unique = true, columnDefinition = "VARCHAR(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    @NotNull(message = "title must not be null")
    String title;

    @Lob
    @NotNull(message = "content must not be null")
    @Column(nullable = false, columnDefinition = "LONGTEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci")
    String content;

    @Column(nullable = false)
    @NotNull(message = "image must not be null")
    String imageUrl;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @JsonManagedReference
    @NotNull(message = "user_id must not be null")
    User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @JsonManagedReference
    @NotNull(message = "category_id must not be null")
    Category category;

    @OneToMany(mappedBy = "article",fetch = FetchType.LAZY)
    @JsonManagedReference
    Set<Comment> comments;

    @OneToMany(mappedBy = "article",fetch = FetchType.LAZY)
    @JsonManagedReference
    Set<Favorite> favorites;

}
