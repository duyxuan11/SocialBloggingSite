package org.example.socialbloggingsite.follows.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;
import org.example.socialbloggingsite.users.dto.UserFollowerResponse;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class FollowResponse {
    UserFolloweeResponse followee;
    List<UserFollowerResponse> followers;
}
