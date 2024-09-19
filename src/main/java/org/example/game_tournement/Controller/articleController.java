package org.example.game_tournement.Controller;

import jakarta.servlet.http.HttpSession;


import jakarta.validation.Valid;
import org.example.game_tournement.Entity.Article;
import org.example.game_tournement.Entity.Tournament;
import org.example.game_tournement.Entity.User;
import org.example.game_tournement.Service.ArticleService;
import org.example.game_tournement.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Controller
public class articleController {
    @Autowired
    HttpSession session;

    private final ArticleService articleService;

    @Autowired
    public articleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Autowired
    AuthService authService;

    @RequestMapping("/creationArticle")
    private String creationArticle(Model model) {
        String username = (String) session.getAttribute("username");
        User user = authService.getUserByName(username);
        if (user.getRoles().equals("ADMIN")) {
            model.addAttribute("user", user);
            model.addAttribute("article",new Article());
            return "CreationArticle";
        }
        return "redirect:/Actualite";
    }

    @PostMapping("/creationArticle")
    private String creationArticle(@Valid @ModelAttribute("article") Article article) {
        articleService.createArticle(article);
        return "redirect:/Actualite";
    }

    //Read One
    @RequestMapping("detailArticle/{articleID}")
    public String detail(@PathVariable("articleID") int id, Model model) {

        Article article = articleService.findById(id);
        model.addAttribute("article",article);

        // Récupérer l'utilisateur connecté
        String username = (String) session.getAttribute("username");
        User user = authService.getUserByName(username);

        // Ajoutez l'utilisateur au modèle
        model.addAttribute("user", user);
        return "detailArticle";
    }

    @RequestMapping("editArticle/{articleID}")
    public String edit(@PathVariable("articleID") int id, Model model) {
        Article article = articleService.findById(id);
        model.addAttribute("article",article);
        String username = (String) session.getAttribute("username");
        User user = authService.getUserByName(username);
        model.addAttribute("user", user);
        return "editArticle";
    }

    @PostMapping("editArticle/{articleID}")
    public String edit(@PathVariable("articleID") int id, @Valid @ModelAttribute("article") Article article) {
        Article article1 = articleService.findById(id);
        article1.setTitle(article.getTitle());
        article1.setCategory(article.getCategory());
        article1.setContent(article.getContent());
        articleService.createArticle(article1);
        return "redirect:/detailArticle/" + id;
    }
}
