package org.example.socialbloggingsite.articles.controller;

import org.example.socialbloggingsite.articles.dtos.UpdateArticleDto;
import org.example.socialbloggingsite.articles.model.Article;
import org.example.socialbloggingsite.articles.service.ArticleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth/article")
@RestController
public class ArticleController {
    private final ArticleService articleService;
    public ArticleController(final ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createArticle(@RequestBody UpdateArticleDto createArticleDto) throws Exception {
        Article article = articleService.createArticle(createArticleDto);
        return ResponseEntity.ok(article);
    }

    @GetMapping("/me")
    public ResponseEntity<?> getArticle()  {
        return ResponseEntity.ok(articleService.getArticle());
    }

}
