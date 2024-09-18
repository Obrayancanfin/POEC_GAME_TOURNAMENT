package org.example.game_tournement.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "tournoi")
public class Tournament {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Ce champ doit être rempli !")
    private String name;
    @NotBlank(message = "Sélectionnez une valeur")
    private String game;
    @NotBlank(message = "Sélectionnez une valeur")
    private String format;
    @NotBlank(message = "Ce champ doit être rempli !")
    private String rules;
    @Min(value = 2, message = "Le nombre de joueurs doit être au moins 2")
    private int numberLimitePlayer;

    @NotNull(message = "La date est obligatoire !")
    private LocalDate dateTournament;
}