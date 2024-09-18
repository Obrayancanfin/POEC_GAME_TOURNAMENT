package org.example.game_tournement.Controller;



import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.game_tournement.Entity.Article;
import org.example.game_tournement.Entity.Tournament;
import org.example.game_tournement.Entity.User;
import org.example.game_tournement.Service.ArticleService;
import org.example.game_tournement.Service.AuthService;
import org.example.game_tournement.Service.tournoisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller

public class tournoisController {
    @Autowired
    HttpSession session;

    @Autowired
    tournoisService tournoisService;

    @Autowired
    AuthService authService;

    //Read ALL
    @RequestMapping("/Actualite")
    private String pageActualite(Model model) {
        List<Article> articles = ArticleService.getAllArticles();
        model.addAttribute("articles", articles);
        return "Actualite";
    }

    @RequestMapping("/tournois")
    public String listTournaments(Model model) {
        List<Tournament> tournaments = tournoisService.getAllTournaments();
        model.addAttribute("tournois", tournaments);

        // Récupérer l'utilisateur connecté
        String username = (String) session.getAttribute("username");
        User user = authService.getUserByName(username);

        // Ajoutez l'utilisateur au modèle
        model.addAttribute("user", user);
        return "ListTournois";
    }

    //Create
    @RequestMapping("/addtournoi")
    private String creationTournoi (Model model) {
        String username = (String) session.getAttribute("username");
        User user = authService.getUserByName(username);
        if (user.getRoles().equals("ADMIN")) {
            model.addAttribute("tournoi", new Tournament());
            return "CreationTournoi";
        }
        return "redirect:/Actualite";
    }

    @PostMapping("/savetournoi")
    private String creationTournoi(@Valid @ModelAttribute("tournoi") Tournament tournoi, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "CreationTournoi";
        }
        tournoisService.addTournament(tournoi);
        return "redirect:/tournois";
    }

    //Read One
    @RequestMapping("detailtournois/{tournoiID}")
    public String detail(@PathVariable("tournoiID") int id, Model model) {
        Tournament tournament = tournoisService.getTournamentById(id);
        model.addAttribute("tournoi", tournament);
        // Récupérer l'utilisateur connecté
        String username = (String) session.getAttribute("username");
        User user = authService.getUserByName(username);

        // Ajoutez l'utilisateur au modèle
        model.addAttribute("user", user);
        return "detailtournois";
    }

    //Update

    //Delete
    @RequestMapping("/deletetournoi/{id}")
    public String showDeleteConfirmation(@PathVariable("id") int id, Model model) {
        Tournament tournament = tournoisService.getTournamentById(id);
        model.addAttribute("tournoi", tournament);
        return "deleteConfirmation";
    }

    // Supprimer le tournoi
    @PostMapping("/deletetournoi/{id}")
    public String deleteTournoi(@PathVariable("id") int id) {
        Tournament tournament = tournoisService.getTournamentById(id);
        tournoisService.deleteTournament(tournament);
        return "redirect:/tournois";
    }
}
