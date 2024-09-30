package org.example.socialbloggingsite.user.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.socialbloggingsite.user.model.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    //Optional<User> findFullName(String fullName);
    Optional<User> findByEmail(String email);
    Optional<User> findById(int id);
    boolean existsByFullName(String fullName);
}
