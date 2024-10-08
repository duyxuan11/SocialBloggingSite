package org.example.socialbloggingsite.users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.example.socialbloggingsite.users.models.UserEntity;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    //Optional<User> findFullName(String fullName);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findById(int id);
    boolean existsByUsername(String fullName);
    Optional<UserEntity> findByUsername(String username);
    boolean existsByEmail(String email);
}
