package org.example.socialbloggingsite.articles.controller;

import org.example.socialbloggingsite.articles.dtos.UpdateArticleDto;
import org.example.socialbloggingsite.articles.model.Article;
import org.example.socialbloggingsite.articles.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<List<Article>> getArticle()  {
        return new ResponseEntity<>(articleService.getArticle(), HttpStatus.OK);
    }

    @PutMapping("/me/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable Long id, @RequestBody UpdateArticleDto updateArticleDto) throws Exception {
        Article article = articleService.updateArticle(updateArticleDto,id);
        return ResponseEntity.ok(article);
    }

}
