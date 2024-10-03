package org.example.socialbloggingsite.follows;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.socialbloggingsite.users.models.UserEntity;
import org.example.socialbloggingsite.utils.BaseEntity;

@Table
@Entity(name = "followers")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FollowerEntity extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followee")
    @JsonManagedReference
    UserEntity followee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower")
    @JsonManagedReference
    UserEntity follower;
}
