package org.example.socialbloggingsite.articles;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.socialbloggingsite.articles.services.impl.ArticleServiceImpl;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/articles")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ArticleController {
    ArticleServiceImpl articleService;

//    @PostMapping("/auth/article/create")
//    public ResponseEntity<?> createArticle(@RequestBody ArticleDtoUpdate createArticleDto) throws Exception {
//        Article article = articleService.createArticle(createArticleDto);
//        return ResponseEntity.ok(article);
//    }
//
//    @GetMapping("/auth/article/me")
//    public ResponseEntity<List<Article>> getArticle()  {
//        return new ResponseEntity<>(articleService.getArticle(), HttpStatus.OK);
//    }
//
//    @PutMapping("/auth/article/me/{id}")
//    public ResponseEntity<?> updateArticle(@PathVariable Long id, @RequestBody ArticleDtoUpdate updateArticleDto) throws Exception {
//        Article article = articleService.updateArticle(updateArticleDto,id);
//        return ResponseEntity.ok(article);
//    }
//
//    @DeleteMapping("/auth/article/me/{id}")
//    public ResponseEntity<?> deleteArticle(@PathVariable Long id) throws Exception {
//        articleService.deleteArticle(id);
//        return ResponseEntity.ok("Deleted article successfully");
//    }
//
//    @GetMapping("/articles/all")
//    public ResponseEntity<List<Article>> getAllArticles() {
//        return new ResponseEntity<>(articleService.getAllArticles(), HttpStatus.OK);
//    }
//
//    @GetMapping("/articles/page/{id}")
//    public ResponseEntity<?> getArticlePage(@PathVariable int id){
//        return new ResponseEntity<>(articleService.getArticlePage(id), HttpStatus.OK);
//    }

}
