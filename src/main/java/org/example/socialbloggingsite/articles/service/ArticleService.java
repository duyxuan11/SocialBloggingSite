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

import java.util.ArrayList;
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

    public List<Article> getArticle() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();
        int id = currentUser.getId();

        List<Article> articles = new ArrayList<>();
        articleRepository.findByUserId(id).forEach(articles::add);

        return articles;
    }

    public Article updateArticle(UpdateArticleDto input, Long id) throws Exception {
        try {
            User user = userRepository.findById(input.getUser_id()).orElseThrow(() -> new Exception("User Not Found"));
            Article article = articleRepository.findById(id).orElseThrow(() -> new Exception("Article Not Found"));
            String title = input.getTitle() == null ? article.getTitle() : input.getTitle();
            String content = input.getContent() == null ? article.getContent() : input.getContent();
            String category = input.getCategory() == null ? article.getCategory() : input.getCategory();
            String published = input.getPublished() == null ? article.getPublished() : input.getPublished();

            article.setTitle(title);
            article.setContent(content);
            article.setCategory(category);
            article.setPublished(published);
            article.setUser(user);
            return articleRepository.save(article);
        }catch (Exception e){
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
}
