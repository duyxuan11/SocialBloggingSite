package org.example.socialbloggingsite.articles.services.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.socialbloggingsite.articles.ArticleEntity;
import org.example.socialbloggingsite.articles.ArticleRepository;
import org.example.socialbloggingsite.articles.dto.ArticleResponse;
import org.example.socialbloggingsite.articles.dto.ArticleUpdateDto;
import org.example.socialbloggingsite.articles.dto.ArticlesCreateDto;
import org.example.socialbloggingsite.articles.services.ArticleService;
import org.example.socialbloggingsite.comments.CommentRepository;
import org.example.socialbloggingsite.comments.dto.CommentArticleResponse;
import org.example.socialbloggingsite.exceptions.customs.CustomRunTimeException;
import org.example.socialbloggingsite.exceptions.customs.ErrorCode;
import org.example.socialbloggingsite.users.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ArticleServiceImpl implements ArticleService {
    ArticleRepository articleRepository;
    UserRepository userRepository;
    ModelMapper modelMapper;
    private final CommentRepository commentRepository;

    @Override
    public ArticleResponse createArticle(ArticlesCreateDto input) {
        if(articleRepository.existsByTitle(input.getTitle())){
            throw new CustomRunTimeException(ErrorCode.TITLE_EXISTS);
        }
        var newArticle = modelMapper.map(input, ArticleEntity.class);

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        var user = userRepository.findByUsername(authentication.getName());
        newArticle.setUser(user.get());
        articleRepository.save(newArticle);
        return modelMapper.map(newArticle, ArticleResponse.class);
    }

    @Override
    public Map<String,Object> getListArticles(Pageable pageable) {
        var articles = articleRepository.findAll(pageable);
        List<ArticleResponse> articleResponses = articles
                .stream()
                .map(article -> modelMapper.map(article, ArticleResponse.class))
                .toList();
        Map<String, Object> response = new HashMap<>();
        response.put("total", articles.getTotalPages());
        response.put("data", articleResponses);
        return response;
    }

    @Override
    public ArticleResponse updateArticle(ArticleUpdateDto input, Long id){
        var oldArticle = articleRepository.findById(id).orElseThrow(() -> new CustomRunTimeException(ErrorCode.ARTICLE_NOT_FOUND));
        var title = input.getTitle() == null ? oldArticle.getTitle() : input.getTitle();
        var content = input.getContent() == null ? oldArticle.getContent() : input.getContent();
        oldArticle.setTitle(title);
        oldArticle.setContent(content);
        articleRepository.save(oldArticle);
        return modelMapper.map(oldArticle, ArticleResponse.class);
    }

    @Override
    public void deleteArticle(Long articleId){
        articleRepository.findById(articleId).orElseThrow(() -> new CustomRunTimeException(ErrorCode.ARTICLE_NOT_FOUND));
        articleRepository.deleteById(articleId);
    }

    @Override
    public ArticleResponse getArticleById(Long articleId) {
        var article = articleRepository.findById(articleId).orElseThrow(()->new CustomRunTimeException(ErrorCode.ARTICLE_NOT_FOUND));
        var comment = commentRepository.findByArticleId(Integer.parseInt(String.valueOf(articleId)));
        var articleResponse = modelMapper.map(article, ArticleResponse.class);
        List<CommentArticleResponse> articleList = comment
                .stream()
                .map(article1 -> modelMapper.map(article1, CommentArticleResponse.class))
                .toList();
        articleResponse.setComments(articleList);
        return articleResponse;
    }
}
