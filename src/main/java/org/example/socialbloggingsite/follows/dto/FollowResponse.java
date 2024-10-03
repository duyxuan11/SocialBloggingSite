package org.example.socialbloggingsite.follows.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FollowResponse {
    int id;
    UserFolloweeResponse followee;
    UserFollowerResponse followers;
}
