package org.example.game_tournement.Controller;

import jakarta.servlet.http.HttpSession;


import org.example.game_tournement.Entity.Tournament;
import org.example.game_tournement.Entity.User;
import org.example.game_tournement.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ArticleController {
    @Autowired
    HttpSession session;

    @Autowired
    org.example.game_tournement.Service.tournoisService tournoisService;

    @Autowired
    AuthService authService;


    @RequestMapping("/addArticle")
    private String creationArticle(Model model) {
        String username = (String) session.getAttribute("username");
        User user = authService.getUserByName(username);
        if (user.getRoles().equals("ADMIN")) {
            model.addAttribute("user", user);
            return "CreationArticle";
        }
        return "redirect:/Actualite";
    }
}
