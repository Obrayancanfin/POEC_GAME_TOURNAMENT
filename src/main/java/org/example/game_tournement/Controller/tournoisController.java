package org.example.game_tournement.Controller;


import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.game_tournement.Entity.Article;
import org.example.game_tournement.Entity.Tournament;
import org.example.game_tournement.Entity.User;
import org.example.game_tournement.Service.ArticleService;
import org.example.game_tournement.Service.AuthService;
import org.example.game_tournement.Service.UserService;
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


    private final ArticleService articleService;

    @Autowired
    public tournoisController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Autowired
    private org.example.game_tournement.Controller.userController userController;

    //Read ALL
    @RequestMapping("/Actualite")
    private String pageActualite(Model model) {
        String username = (String) session.getAttribute("username");
        User user = authService.getUserByName(username);
        List<Article> articles = articleService.getAllArticles();
        model.addAttribute("articles", articles);
        model.addAttribute("user", user);
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
    private String creationTournoi(@Valid @ModelAttribute("tournoi") Tournament tournoi, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("isUpdate", false);

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

    // Update - GET
    @RequestMapping("/update/{tournamentID}")
    public String update(@PathVariable("tournamentID") int id, Model model) {
        String username = (String) session.getAttribute("username");
        User user = authService.getUserByName(username);
        if (user != null && user.getRoles().equals("ADMIN")) {
            Tournament tournament = tournoisService.getTournamentById(id);
            model.addAttribute("tournoi", tournament);
            model.addAttribute("isUpdate", true);
            System.out.println("au début");
            System.out.println(tournament);
            return "CreationTournoi";
        }
        return "redirect:/tournois";
    }

    // Update - POST
    @PostMapping("/update/{tournamentID}")
    public String updateTournament(@PathVariable("tournamentID") int id,
                                   @Valid @ModelAttribute("tournament") Tournament tournament,
                                   BindingResult bindingResult,
                                   Model model) {
        if (bindingResult.hasErrors()) {
            return "CreationTournoi";
        }
        tournament.setId(id); // Assurez-vous que l'ID est bien mis à jour
        System.out.println("a la fin");
        System.out.println(tournament);
        tournoisService.addTournament(tournament);
        return "redirect:/detailtournois/" + tournament.getId();
    }

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
