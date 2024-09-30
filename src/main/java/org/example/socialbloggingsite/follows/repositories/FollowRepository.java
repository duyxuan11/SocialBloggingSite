package org.example.socialbloggingsite.follows.repositories;

import org.example.socialbloggingsite.follows.models.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowRepository extends JpaRepository<Follower, Long> {
}
