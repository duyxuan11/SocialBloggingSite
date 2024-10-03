package org.example.socialbloggingsite.favorites;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.socialbloggingsite.articles.ArticleEntity;
import org.example.socialbloggingsite.users.models.UserEntity;
import org.example.socialbloggingsite.utils.BaseEntity;

@Table
@Entity(name = "favorites")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FavoriteEntity extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @JsonManagedReference
    UserEntity user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    @JsonManagedReference
    ArticleEntity article;
}
