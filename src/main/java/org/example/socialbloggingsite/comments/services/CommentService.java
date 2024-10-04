package org.example.socialbloggingsite.comments.services;

import org.example.socialbloggingsite.comments.dto.CommentCreateDto;
import org.example.socialbloggingsite.comments.dto.CommentResponse;

import java.util.Map;

public interface CommentService {
    Map<String,Object> getCommentsbyArticleId(Integer articleId);
    CommentResponse sendComment(Long articleId, CommentCreateDto input);
    void deleteComment(Long commentId);
}
