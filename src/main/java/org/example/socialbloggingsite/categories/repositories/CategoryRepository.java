package org.example.socialbloggingsite.categories.repositories;

import org.example.socialbloggingsite.articles.model.Article;
import org.example.socialbloggingsite.categories.models.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
