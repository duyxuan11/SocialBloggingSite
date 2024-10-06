package org.example.socialbloggingsite.follows.services.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.socialbloggingsite.exceptions.customs.CustomRunTimeException;
import org.example.socialbloggingsite.exceptions.customs.ErrorCode;
import org.example.socialbloggingsite.follows.FollowRepository;
import org.example.socialbloggingsite.follows.FollowerEntity;
import org.example.socialbloggingsite.follows.dto.FollowResponse;
import org.example.socialbloggingsite.follows.dto.UserFolloweeResponse;
import org.example.socialbloggingsite.follows.services.FollowService;
import org.example.socialbloggingsite.users.dto.UserFollowerResponse;
import org.example.socialbloggingsite.users.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class FollowServiceImpl implements FollowService {

    UserRepository userRepository;
    private final FollowRepository followRepository;
    private final ModelMapper modelMapper;

    @Override
    public FollowResponse getFollowByUserId(Integer userId){
        var user = userRepository.findById(userId).orElseThrow(()->new CustomRunTimeException(ErrorCode.USER_NOT_FOUND));
        var followResponse = followRepository.findByFolloweeId(userId);
        if (followResponse.isEmpty()){
            return new FollowResponse();
        }
        List<UserFollowerResponse> followers = new ArrayList<>();
        followResponse.forEach(followerEntity -> {
            var follower = modelMapper.map(followerEntity.getFollower(), UserFollowerResponse.class);
            followers.add(follower);
        });
        FollowResponse followResponseResponse = FollowResponse.builder().followers(followers).followee(modelMapper.map(user,UserFolloweeResponse.class)).build();
        return followResponseResponse;
    }

    @Override
    public FollowResponse followUser(Integer userId){
        var userFollowee = userRepository.findById(userId).orElseThrow(()->new CustomRunTimeException(ErrorCode.USER_NOT_FOUND));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var userFollower = userRepository.findByUsername(authentication.getName());

        FollowerEntity followerEntity = FollowerEntity.builder().follower(userFollower.get()).followee(userFollowee).build();
        followRepository.save(followerEntity);

        var followee = modelMapper.map(userFollowee, UserFolloweeResponse.class);
        var follower = modelMapper.map(userFollower, UserFollowerResponse.class);
        List<UserFollowerResponse> followerList = new ArrayList<>();
        followerList.add(follower);
        FollowResponse followResponse = FollowResponse.builder().followee(followee).followers(followerList).build();
        return followResponse;
    }

    @Override
    public void unFollow(Integer userId){
        var userFollowee = userRepository.findById(userId).orElseThrow(()->new CustomRunTimeException(ErrorCode.USER_NOT_FOUND));
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var userFollower = userRepository.findByUsername(authentication.getName());

        var follow = followRepository.findByFolloweeIdAndFollowerId(userFollowee.getId(),userFollower.get().getId());
        followRepository.delete(follow.get());
    }
}
