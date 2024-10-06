package org.example.socialbloggingsite.follows;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FollowRepository extends JpaRepository<FollowerEntity, Long> {
    List<FollowerEntity> findByFolloweeId(int userId);
    Optional<FollowerEntity> findByFolloweeIdAndFollowerId(int followeeId, int followerId);
}
