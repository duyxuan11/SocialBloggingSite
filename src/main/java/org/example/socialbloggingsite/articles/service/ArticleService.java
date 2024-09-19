package org.example.socialbloggingsite.articles.service;

import org.example.socialbloggingsite.articles.dtos.UpdateArticleDto;
import org.example.socialbloggingsite.articles.model.Article;
import org.example.socialbloggingsite.articles.repositories.ArticleRepository;
import org.example.socialbloggingsite.user.Repositories.UserRepository;
import org.example.socialbloggingsite.user.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private final ArticleRepository articleRepository;
    private final UserRepository userRepository;
    User user;
    public ArticleService(ArticleRepository articleRepository, UserRepository userRepository) {
        this.articleRepository = articleRepository;
        this.userRepository = userRepository;
    }

    public Article createArticle(UpdateArticleDto input) throws Exception {
        try {
            if(input.getTitle() == null || input.getUser_id() == 0 || input.getContent() == null){
                throw new Exception("Title name or content is required");
            }
            User user = userRepository.findById(input.getUser_id()).orElseThrow(() -> new Exception("User Not Found"));
            var article = new Article()
                    .setTitle(input.getTitle())
                    .setContent(input.getContent())
                    .setCategory(input.getCategory())
                    .setPublished(input.getPublished())
                    .setUser(user);
            return articleRepository.save(article);
        }catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    public ResponseEntity<?> getArticle() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();
        int id = currentUser.getId();

        List<Article> article = articleRepository.findByUserId(id);
        if(!article.isEmpty()){
            return ResponseEntity.ok(article);
        }
        return ResponseEntity.notFound().build();
    }
}
