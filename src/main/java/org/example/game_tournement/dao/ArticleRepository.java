package org.example.game_tournement.dao;

import org.example.game_tournement.Entity.Article;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Integer> {
    List<Article> findByTitle(String title);
    Article findById(int id);
}
