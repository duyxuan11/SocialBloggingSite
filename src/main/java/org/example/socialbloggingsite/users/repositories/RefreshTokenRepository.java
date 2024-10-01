package org.example.socialbloggingsite.users.repositories;

import org.example.socialbloggingsite.users.models.RefreshTokenEntity;
import org.example.socialbloggingsite.users.models.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshTokenEntity, Long> {
    Optional<RefreshTokenEntity> findByToken(String token);
    Boolean existsByToken(String token);
//    Optional<RefreshTokenEntity> findByUserName(String userName);

    @Modifying
    int deleteByUser(UserEntity user);

    Optional<RefreshTokenEntity> findByUser(UserEntity user);
}
