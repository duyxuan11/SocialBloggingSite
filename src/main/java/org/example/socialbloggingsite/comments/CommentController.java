package org.example.socialbloggingsite.comments;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.socialbloggingsite.comments.dto.CommentCreateDto;
import org.example.socialbloggingsite.comments.services.CommentService;
import org.example.socialbloggingsite.comments.services.impl.CommentServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/comments")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CommentController {

    CommentService commentService;

    @GetMapping("/{articleId}")
    public ResponseEntity<?> getCommentsByArticleId(@PathVariable Integer articleId)  {
        if(articleId == null) {
            return ResponseEntity.badRequest().body("Invalid ID supplied");
        }
        var article = commentService.getCommentsbyArticleId(articleId);
        return ResponseEntity.ok(article);
    }

    @PostMapping("/{articleId}")
    public ResponseEntity<?> addComment(@PathVariable Long articleId, @RequestBody @Valid CommentCreateDto request) {
        if(articleId == null) {
            return ResponseEntity.badRequest().body("Invalid ID supplied");
        }
        var comment = commentService.sendComment(articleId, request);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping("/{commentId}")
    public ResponseEntity<?> deleteComment(@PathVariable Long commentId) {
        if(commentId == null) {
            return ResponseEntity.badRequest().body("Invalid ID supplied");
        }
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("Comment deleted Successfully");
    }
}
