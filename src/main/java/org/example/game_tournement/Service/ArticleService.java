package org.example.game_tournement.Service;

import org.example.game_tournement.Entity.Article;
import org.example.game_tournement.Entity.Tournament;
import org.example.game_tournement.dao.ArticleRepository;
import org.example.game_tournement.dao.MatchsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleService {
    private static ArticleRepository articleRepository;
    public ArticleService(ArticleRepository articleRepository) {
        ArticleService.articleRepository = articleRepository;
    }
    public static List<Article> getAllArticles() {
        return articleRepository.findAll();
    }
}
