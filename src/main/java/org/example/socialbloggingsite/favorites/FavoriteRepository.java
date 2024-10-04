package org.example.socialbloggingsite.favorites;

import org.example.socialbloggingsite.articles.ArticleEntity;
import org.example.socialbloggingsite.users.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<FavoriteEntity, Long> {
    Optional<FavoriteEntity> findByArticleAndUser(ArticleEntity article, UserEntity user);


    @Query("SELECT b FROM favorites b " +
            "JOIN articles r ON r.id = b.article.id " +
            "JOIN users u ON u.id = r.user.id " +
            "WHERE r.id = :id")
    List<FavoriteEntity> findByArticleId(@Param("id") int id);
}
