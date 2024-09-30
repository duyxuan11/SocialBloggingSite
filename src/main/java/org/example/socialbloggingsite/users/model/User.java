package org.example.socialbloggingsite.users.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.socialbloggingsite.articles.model.Article;
import org.example.socialbloggingsite.categories.models.Category;
import org.example.socialbloggingsite.comments.models.Comment;
import org.example.socialbloggingsite.favorites.models.Favorite;
import org.example.socialbloggingsite.follows.models.Follower;
import org.example.socialbloggingsite.utils.base.BaseEntity;
import org.example.socialbloggingsite.utils.constants.Gender;
import org.example.socialbloggingsite.utils.constants.Role;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity(name = "users")
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
@EntityListeners(AuditingEntityListener.class)
public class User extends BaseEntity implements UserDetails {
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

    @Column(unique = true, nullable = false)
    @NotNull(message = "userName must be not null")
    String username;

    @Column(nullable = false)
    @NotNull(message = "password must be not null")
    String password;

    @Column(unique = true, nullable = false)
    @NotNull(message = "email must be not null")
    String email;

    @Column(nullable = false,columnDefinition = "TEXT")
    @NotNull(message = "image must be not null")
    String imageUrl;

    String firstName;
    String lastName;
    Date birthDay;

    @Enumerated(EnumType.STRING)
    Gender gender;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    @NotNull(message = "role must be not null")
    Role role;

    @CreatedBy
    String createdBy;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference
    Set<Article> articles;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference
    Set<Comment> comments;

    //favorites
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference
    Set<Favorite> favorites;

    //Follow users
    @OneToMany(mappedBy = "follower", fetch = FetchType.LAZY)
    @JsonBackReference
    List<Follower> follower;

    @OneToMany(mappedBy = "followee", fetch = FetchType.LAZY)
    @JsonBackReference
    List<Follower> followee;
}
