package org.example.socialbloggingsite.comments;

import org.example.socialbloggingsite.comments.dto.CommentArticleResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    @Query("SELECT b FROM comments b WHERE b.article.id = :articleId")
    List<CommentEntity> findByArticleId(@Param("articleId") Integer articleId);
}
