package org.example.socialbloggingsite.articles.services;

import org.example.socialbloggingsite.articles.dto.ArticleResponse;
import org.example.socialbloggingsite.articles.dto.ArticleUpdateDto;
import org.example.socialbloggingsite.articles.dto.ArticlesCreateDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;

public interface ArticleService {
    ArticleResponse getArticleById(Long articleId);
    ArticleResponse createArticle(ArticlesCreateDto articlesCreateDto);
    ArticleResponse updateArticle(ArticleUpdateDto articleUpdateDto, Long articleId);
    Map<String,Object> getListArticles(Pageable pageable);
    void deleteArticle(Long articleId);
}
