package org.example.socialbloggingsite.articles.controller;

import org.example.socialbloggingsite.articles.dtos.UpdateArticleDto;
import org.example.socialbloggingsite.articles.model.Article;
import org.example.socialbloggingsite.articles.service.ArticleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RequestMapping("/auth/article")
@RestController
public class ArticleController {
    private final ArticleService articleService;
    public ArticleController(final ArticleService articleService) {
        this.articleService = articleService;
    }

    @PostMapping("/auth/article/create")
    public ResponseEntity<?> createArticle(@RequestBody UpdateArticleDto createArticleDto) throws Exception {
        Article article = articleService.createArticle(createArticleDto);
        return ResponseEntity.ok(article);
    }

    @GetMapping("/auth/article/me")
    public ResponseEntity<List<Article>> getArticle()  {
        return new ResponseEntity<>(articleService.getArticle(), HttpStatus.OK);
    }

    @PutMapping("/auth/article/me/{id}")
    public ResponseEntity<?> updateArticle(@PathVariable Long id, @RequestBody UpdateArticleDto updateArticleDto) throws Exception {
        Article article = articleService.updateArticle(updateArticleDto,id);
        return ResponseEntity.ok(article);
    }

    @DeleteMapping("/auth/article/me/{id}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long id) throws Exception {
        articleService.deleteArticle(id);
        return ResponseEntity.ok("Deleted article successfully");
    }

    @GetMapping("/articles/all")
    public ResponseEntity<List<Article>> getAllArticles() {
        return new ResponseEntity<>(articleService.getAllArticles(), HttpStatus.OK);
    }

    @GetMapping("/articles/page/{id}")
    public ResponseEntity<?> getArticlePage(@PathVariable int id){
        return new ResponseEntity<>(articleService.getArticlePage(id), HttpStatus.OK);
    }

}
