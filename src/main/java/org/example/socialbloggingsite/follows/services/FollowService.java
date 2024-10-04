package org.example.socialbloggingsite.follows.services;

import org.example.socialbloggingsite.follows.dto.FollowResponse;

public interface FollowService {
    FollowResponse getFollowByUserId(Integer userId);
    FollowResponse followUser(Integer userId);
    void unFollow(Integer userId);
}
