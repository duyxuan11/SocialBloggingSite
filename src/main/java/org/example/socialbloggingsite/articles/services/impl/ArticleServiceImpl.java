package org.example.socialbloggingsite.articles.services.impl;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.example.socialbloggingsite.articles.ArticleRepository;
import org.example.socialbloggingsite.articles.services.ArticleService;
import org.example.socialbloggingsite.users.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ArticleServiceImpl implements ArticleService {
    ArticleRepository articleRepository;
    UserRepository userRepository;

//    public Article createArticle(ArticleDtoUpdate input) throws Exception {
//        try {
//            if(input.getTitle() == null || input.getUser_id() == 0 || input.getContent() == null){
//                throw new Exception("Title name or content is required");
//            }
//            User user = userRepository.findById(input.getUser_id()).orElseThrow(() -> new Exception("User Not Found"));
//            var article = new Article()
//                    .setTitle(input.getTitle())
//                    .setContent(input.getContent())
//                    .setCategory(input.getCategory())
//                    .setPublished(input.getPublished())
//                    .setUser(user);
//            return articleRepository.save(article);
//        }catch (Exception e) {
//            e.printStackTrace();
//            throw new Exception(e.getMessage());
//        }
//    }

//    public List<Article> getArticle() {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//
//        User currentUser = (User) authentication.getPrincipal();
//        int id = currentUser.getId();
//
//        List<Article> articles = new ArrayList<>();
//        articleRepository.findByUserId(id).forEach(articles::add);
//
//        return articles;
//    }
//
//    public Article updateArticle(ArticleDtoUpdate input, Long id) throws Exception {
//        try {
//            User user = userRepository.findById(input.getUser_id()).orElseThrow(() -> new Exception("User Not Found"));
//            Article article = articleRepository.findById(id).orElseThrow(() -> new Exception("Article Not Found"));
//            String title = input.getTitle() == null ? article.getTitle() : input.getTitle();
//            String content = input.getContent() == null ? article.getContent() : input.getContent();
//            String category = input.getCategory() == null ? article.getCategory() : input.getCategory();
//            String published = input.getPublished() == null ? article.getPublished() : input.getPublished();
//
//            article.setTitle(title);
//            article.setContent(content);
//            article.setCategory(category);
//            article.setPublished(published);
//            article.setUser(user);
//            return articleRepository.save(article);
//        }catch (Exception e){
//            e.printStackTrace();
//            throw new Exception(e.getMessage());
//        }
//    }
//
//    public void deleteArticle(Long id) throws Exception {
//        try {
//            articleRepository.findById(id).orElseThrow(() -> new Exception("Article Not Found"));
//            articleRepository.deleteById(id);
//        }catch (Exception e){
//            e.printStackTrace();
//            throw new Exception(e.getMessage());
//        }
//
//    }
//
//    public List<Article> getAllArticles() {
//        List<Article> articles = new ArrayList<>();
//        articleRepository.findAll().forEach(articles::add);
//
//        return articles;
//    }
//
//    public List<Article> getArticlePage(int id){
//        Page<Article> page = articleRepository.findAll(PageRequest.of((id-1), 5));
//        return page.getContent();
//    }
}
