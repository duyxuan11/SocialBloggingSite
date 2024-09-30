package org.example.socialbloggingsite.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.socialbloggingsite.articles.model.Article;
import org.example.socialbloggingsite.utils.base.BaseEntity;
import org.example.socialbloggingsite.utils.constants.Gender;
import org.example.socialbloggingsite.utils.constants.Role;
import org.hibernate.annotations.UpdateTimestamp;
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
public class User extends BaseEntity {
    @Column(unique = true, nullable = false)
    @NotNull(message = "userName must be not null")
    String username;

    @Column(nullable = false)
    @NotNull(message = "password must be not null")
    String password;

    @Column(unique = true, nullable = false)
    @NotNull(message = "email must be not null")
    String email;

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
    Set<Favourite> comments;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    @JsonManagedReference
    Set<Comment> comments;

}
