package org.example.socialbloggingsite.follows.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.socialbloggingsite.users.model.User;
import org.example.socialbloggingsite.utils.base.BaseEntity;

import java.util.Set;

@Table
@Entity(name = "followers")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Follower extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "followee")
    @JsonManagedReference
    User followee;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "follower")
    @JsonManagedReference
    User follower;
}
