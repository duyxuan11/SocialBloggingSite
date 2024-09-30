package org.example.socialbloggingsite.comments.repositories;

import org.example.socialbloggingsite.comments.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
