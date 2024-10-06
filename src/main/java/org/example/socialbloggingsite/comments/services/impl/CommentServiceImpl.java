package org.example.socialbloggingsite.comments.services.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.socialbloggingsite.articles.ArticleRepository;
import org.example.socialbloggingsite.comments.CommentEntity;
import org.example.socialbloggingsite.comments.CommentRepository;
import org.example.socialbloggingsite.comments.dto.CommentArticleResponse;
import org.example.socialbloggingsite.comments.dto.CommentCreateDto;
import org.example.socialbloggingsite.comments.dto.CommentResponse;
import org.example.socialbloggingsite.comments.services.CommentService;
import org.example.socialbloggingsite.exceptions.customs.CustomRunTimeException;
import org.example.socialbloggingsite.exceptions.customs.ErrorCode;
import org.example.socialbloggingsite.users.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CommentServiceImpl implements CommentService {

    CommentRepository commentRepository;
    ModelMapper modelMapper;
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;

    @Override
    @Transactional
    public Map<String,Object> getCommentsbyArticleId(Integer articleId){
        var articles = commentRepository.findByArticleId(articleId);
        List<CommentArticleResponse> articleResponses = articles
                .stream()
                .map(article -> modelMapper.map(article, CommentArticleResponse.class))
                .toList();
        Map<String,Object> map = new HashMap<>();
        map.put("total",articleResponses.size());
        map.put("data",articleResponses);
        return map;
    }

    @Override
    @Transactional
    public CommentResponse sendComment(Long articleId, CommentCreateDto input){
        var article = articleRepository.findById(articleId).orElseThrow(()->new CustomRunTimeException(ErrorCode.ARTICLE_NOT_FOUND));
        var newComment = modelMapper.map(input, CommentEntity.class);
        modelMapper.map(newComment, article);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = userRepository.findByUsername(authentication.getName());
        newComment.setArticle(article);
        newComment.setUser(user.get());
        return modelMapper.map(commentRepository.save(newComment), CommentResponse.class);
    }

    @Override
    @Transactional
    public void deleteComment(Long commentId){
        var comment = commentRepository.findById(commentId).orElseThrow(()-> new CustomRunTimeException(ErrorCode.COMMENT_NOT_FOUND));
        commentRepository.delete(comment);
    }
}
