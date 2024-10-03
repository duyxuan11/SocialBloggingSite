package org.example.socialbloggingsite.users.repositories;

import org.example.socialbloggingsite.users.models.RefreshTokenEntity;
import org.example.socialbloggingsite.users.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    Boolean existsByUser(UserEntity user);
}
