package org.example.socialbloggingsite.favorites.repositories;

import org.example.socialbloggingsite.favorites.models.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
}
