package org.example.socialbloggingsite.articles.repositories;

import org.example.socialbloggingsite.articles.model.Article;
import org.example.socialbloggingsite.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    Optional<Article> findById(int id);

    Optional<Article> findByUserId(int user_id);
}
