package org.example.socialbloggingsite.follows;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.socialbloggingsite.follows.services.FollowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/follows")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class FollowController {

    FollowService followService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getFollowByUserId(@PathVariable("userId") Integer userId) {
        if (userId == null) {
            return ResponseEntity.badRequest().body("Invalid ID supplied");
        }
        return ResponseEntity.ok(followService.getFollowByUserId(userId));
    }

    @PostMapping("/{userId}")
    public ResponseEntity<?> createFollow(@PathVariable("userId") Integer userId) {
        if (userId == null) {
            return ResponseEntity.badRequest().body("Invalid ID supplied");
        }
        return ResponseEntity.ok(followService.followUser(userId));
    }

    @DeleteMapping("/{userId}")
    public ResponseEntity<?> deleteFollow(@PathVariable("userId") Integer userId) {
        if (userId == null) {
            return ResponseEntity.badRequest().body("Invalid ID supplied");
        }
        followService.unFollow(userId);
        return ResponseEntity.ok("Unfollow successfully");
    }
}
