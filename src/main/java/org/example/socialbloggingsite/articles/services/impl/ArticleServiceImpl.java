package org.example.socialbloggingsite.articles.services.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.socialbloggingsite.articles.ArticleEntity;
import org.example.socialbloggingsite.articles.ArticleRepository;
import org.example.socialbloggingsite.articles.dto.ArticleResponse;
import org.example.socialbloggingsite.articles.dto.ArticleUpdateDto;
import org.example.socialbloggingsite.articles.dto.ArticlesCreateDto;
import org.example.socialbloggingsite.articles.services.ArticleService;
import org.example.socialbloggingsite.exceptions.customs.CustomRunTimeException;
import org.example.socialbloggingsite.exceptions.customs.ErrorCode;
import org.example.socialbloggingsite.users.repositories.UserRepository;
import org.example.socialbloggingsite.users.services.AuthenticationService;
import org.example.socialbloggingsite.users.services.UserService;
import org.example.socialbloggingsite.utils.JwtUtils;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ArticleServiceImpl implements ArticleService {
    ArticleRepository articleRepository;
    UserRepository userRepository;
    ModelMapper modelMapper;

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
        response.put("articles", articleResponses);
        return response;
    }

    @Override
    public ArticleResponse updateArticle(ArticleUpdateDto input, Long id){
        var oldArticle = articleRepository.findById(id).orElseThrow(() -> new CustomRunTimeException(ErrorCode.ARTICLE_NOT_FOUND));
        var newArticle = modelMapper.map(input, ArticleEntity.class);
        modelMapper.map(oldArticle, newArticle);
        articleRepository.save(newArticle);
        return modelMapper.map(newArticle, ArticleResponse.class);
    }

    @Override
    public void deleteArticle(Long articleId){
        articleRepository.findById(articleId).orElseThrow(() -> new CustomRunTimeException(ErrorCode.ARTICLE_NOT_FOUND));
        articleRepository.deleteById(articleId);
    }

    @Override
    public ArticleResponse getArticleById(Long articleId) {
        var article = articleRepository.findById(articleId).orElseThrow(()->new CustomRunTimeException(ErrorCode.ARTICLE_NOT_FOUND));
        return modelMapper.map(article, ArticleResponse.class);
    }
}
