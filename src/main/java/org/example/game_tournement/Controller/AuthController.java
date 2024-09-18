package org.example.game_tournement.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.game_tournement.Entity.User;
import org.example.game_tournement.Service.AuthService;
import org.example.game_tournement.Service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AuthController {
    private final AuthService authService;
    private final HttpSession session;

    public AuthController(AuthService authService, HttpSession session) {
        this.authService = authService;
        this.session = session;
    }

    @RequestMapping("/")
    private String pageAccueil(Model model) {
        if (authService.isLogged()) {
            // Récupérer le nom d'utilisateur depuis la session
            String username = (String) session.getAttribute("username");
            // Récupérer l'utilisateur depuis le repository
            User user = authService.getUserByName(username);

            // Ajoutez le nom d'utilisateur et le rôle au modèle
            model.addAttribute("username", user.getUsername());
            model.addAttribute("roles", user.getRoles());
        }
        return "Accueil";
    }

    @RequestMapping("/login")
    public String connexion(Model model){
        return "connexion-form";
    }

    @PostMapping("/login")
    public String connexionForm(@ModelAttribute("username") String username,
                                @ModelAttribute("password") String password){
        boolean connected = authService.login(username, password);
        if(connected){
            return "redirect:/Actualite";
        }
        return "redirect:/login";
    }

    @RequestMapping("/logout")
    public String logout(){
        authService.logout();
        return "redirect:/login";
    }
}

