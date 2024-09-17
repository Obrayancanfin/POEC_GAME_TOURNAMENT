package org.example.game_tournement.Controller;

import org.example.game_tournement.Entity.User;
import org.example.game_tournement.Service.AuthService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @RequestMapping("/")
    private String pageAccueil(){
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
}
