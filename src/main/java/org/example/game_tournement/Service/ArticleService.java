package org.example.game_tournement.Service;

import org.example.game_tournement.Entity.Article;
import org.example.game_tournement.dao.ArticleRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {
    private static ArticleRepository articleRepository;

    public ArticleService(ArticleRepository articleRepository) {
        ArticleService.articleRepository = articleRepository;
    }

    public Article findById(int id) {
        return articleRepository.findById(id);
    }

    ;

    public  List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    public  List<Article> getArticleByTitle(String title) {
        return articleRepository.findByTitle(title);
    }

    public void createArticle(Article article) {
        articleRepository.save(article);
    }
}
