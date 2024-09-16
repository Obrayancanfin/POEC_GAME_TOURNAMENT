package org.example.game_tournement.Controller;



import org.example.game_tournement.Entity.Tournament;
import org.example.game_tournement.Entity.User;
import org.example.game_tournement.Service.tournoisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller

public class tournoisController {

    @Autowired
    tournoisService tournoisService;


    //Read ALL
    @RequestMapping("/Actualité")
    private String pageActualite(Model model) {
        List<Tournament> tournaments = tournoisService.getAllTournaments();
        model.addAttribute("tournements", tournaments);
        return "Actualite";
    }

    //Create
    @RequestMapping("/addtournoi")
    private String creationTournoi (Model model) {
        model.addAttribute("tournoi", new Tournament());
        return "CreationTournoi";
    }
    @PostMapping("/savetournoi")
    private String creationTournoi(@ModelAttribute("tournoi") Tournament tournoi) {
        return "Actualite";
    }


    //Read One

    //Update

    //Delete
}
