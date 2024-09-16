package org.example.game_tournement.Controller;

import jakarta.validation.Valid;
import org.example.game_tournement.Entity.User;
import org.example.game_tournement.Service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping("/ReadUserTest")
    private String readUserTest(Model model) {
        model.addAttribute("users", userService.read());
        return "ReadUserTest";
    }

    @RequestMapping("/InscriptionUtilisateur")
    private String pageInscriptionUtilisateur(Model model) {
        model.addAttribute("user", new User());
        return "InscriptionUtilisateur";
    }

    @PostMapping("/confirmationInscription")
    private String confirmationInscription(@Valid @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "InscriptionUtilisateur";
        } else {
            userService.createStudent(user);
        }
        return "redirect:/login";
    }

    @RequestMapping("/InscriptionTournois")
    private String pageInscriptionTournois(Model model) {
        User user = new User();
        model.addAttribute("User", userService.getUserByName(user.getUsername()));
        return "InscriptionTournois";
    }
}