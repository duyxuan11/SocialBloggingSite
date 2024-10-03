package org.example.socialbloggingsite.articles;

import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.example.socialbloggingsite.articles.dto.ArticleUpdateDto;
import org.example.socialbloggingsite.articles.dto.ArticlesCreateDto;
import org.example.socialbloggingsite.articles.services.ArticleService;
import org.example.socialbloggingsite.articles.services.impl.ArticleServiceImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/articles")
@RestController
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
@Slf4j
public class ArticleController {
    ArticleService articleService;
    final static String DEFAULT_FILTER_PAGE = "0";
    final static String DEFAULT_FILTER_SiZE = "10";
    final static Sort DEFAULT_FILTER_SORT = Sort.by(Sort.Direction.DESC, "createdAt");
    final static Sort DEFAULT_FILTER_SORT_ASC = Sort.by(Sort.Direction.ASC, "createdAt");

    @PostMapping()
    public ResponseEntity<?> createArticle(@RequestBody @Valid ArticlesCreateDto request){
        var article = articleService.createArticle(request);
        return ResponseEntity.ok(article);
    }

    @GetMapping("/{articleId}")
    public ResponseEntity<?> getArticleById(@PathVariable Long articleId)  {
        if(articleId == null) {
            return ResponseEntity.badRequest().body("Invalid ID supplied");
        }
        var article = articleService.getArticleById(articleId);
        return ResponseEntity.ok(article);
    }

    @PutMapping("/{articleId}")
    public ResponseEntity<?> updateArticle(@PathVariable Long articleId, @RequestBody(required = false) ArticleUpdateDto request){
        if(articleId == null) {
            return ResponseEntity.badRequest().body("Invalid ID supplied");
        }
        var article = articleService.updateArticle(request,articleId);
        return ResponseEntity.ok(article);
    }

    @DeleteMapping("/{articleId}")
    public ResponseEntity<?> deleteArticle(@PathVariable Long articleId){
        if(articleId == null) {
            return ResponseEntity.badRequest().body("Invalid ID supplied");
        }
        articleService.deleteArticle(articleId);
        return ResponseEntity.ok("Article deleted successfully");
    }

    @GetMapping()
    public ResponseEntity<?> getListArticles(
            @RequestParam(required = false, defaultValue = DEFAULT_FILTER_PAGE) int page,
            @RequestParam(required = false, defaultValue = DEFAULT_FILTER_SiZE) int size,
            @RequestParam(required = false) String sortParam
    ) {
        Sort sort = DEFAULT_FILTER_SORT;
        if(sortParam!=null && sortParam.equals("ASC")){
            sort = DEFAULT_FILTER_SORT_ASC;
        }
        Pageable pageable = PageRequest.of(page, size, sort);
        var listArticles = articleService.getListArticles(pageable);
        return ResponseEntity.ok(listArticles);
    }
}
