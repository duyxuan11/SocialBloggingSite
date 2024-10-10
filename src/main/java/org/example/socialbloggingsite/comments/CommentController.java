package org.example.socialbloggingsite.comments;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.socialbloggingsite.comments.dto.CommentCreateDto;
import org.example.socialbloggingsite.comments.services.CommentService;
import org.example.socialbloggingsite.comments.services.impl.CommentServiceImpl;
import org.example.socialbloggingsite.exceptions.customs.CustomRunTimeException;
import org.example.socialbloggingsite.exceptions.customs.ErrorCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/api/comments")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class CommentController {

    CommentService commentService;

    @GetMapping(value = {"/","/{articleId}"})
    public ResponseEntity<?> getCommentsByArticleId(@PathVariable Optional<Integer> articleId)  {
        if(articleId.isEmpty()) {
            throw new CustomRunTimeException(ErrorCode.COMMENT_ID_INVALID);
        }
        var article = commentService.getCommentsbyArticleId(articleId.get());
        return ResponseEntity.ok(article);
    }

    @PostMapping(value = {"/","/{articleId}"})
    public ResponseEntity<?> addComment(@PathVariable Optional<Long> articleId, @RequestBody @Valid CommentCreateDto request) {
        if(articleId.isEmpty()) {
            throw new CustomRunTimeException(ErrorCode.COMMENT_ID_INVALID);
        }
        var comment = commentService.sendComment(articleId.get(), request);
        return ResponseEntity.ok(comment);
    }

    @DeleteMapping(value = {"/","/{commentId}"})
    public ResponseEntity<?> deleteComment(@PathVariable Optional<Long> commentId) {
        if(commentId.isEmpty()) {
            throw new CustomRunTimeException(ErrorCode.COMMENT_ID_INVALID);
        }
        commentService.deleteComment(commentId.get());
        return ResponseEntity.ok("Comment deleted Successfully");
    }
}
