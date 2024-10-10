package org.example.socialbloggingsite.follows;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.socialbloggingsite.exceptions.customs.CustomRunTimeException;
import org.example.socialbloggingsite.exceptions.customs.ErrorCode;
import org.example.socialbloggingsite.follows.services.FollowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/api/follows")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class FollowController {

    FollowService followService;

    @GetMapping(value = {"/","/{userId}"})
    public ResponseEntity<?> getFollowByUserId(@PathVariable("userId") Optional<Integer> userId) {
        if (userId.isEmpty()) {
            throw new CustomRunTimeException(ErrorCode.FAVORITE_ID_INVALID);
        }
        return ResponseEntity.ok(followService.getFollowByUserId(userId.get()));
    }

    @PostMapping(value = {"/","/{userId}"})
    public ResponseEntity<?> createFollow(@PathVariable("userId") Optional<Integer> userId) {
        if (userId.isEmpty()) {
            throw new CustomRunTimeException(ErrorCode.FAVORITE_ID_INVALID);
        }
        return ResponseEntity.ok(followService.followUser(userId.get()));
    }

    @DeleteMapping(value = {"/","/{userId}"})
    public ResponseEntity<?> deleteFollow(@PathVariable("userId") Optional<Integer> userId) {
        if (userId.isEmpty()) {
            throw new CustomRunTimeException(ErrorCode.FAVORITE_ID_INVALID);
        }
        followService.unFollow(userId.get());
        return ResponseEntity.ok("Unfollow successfully");
    }
}
