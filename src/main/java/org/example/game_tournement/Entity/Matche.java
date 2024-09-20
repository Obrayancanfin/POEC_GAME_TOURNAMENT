package org.example.game_tournement.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Matche {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_tournoi")
    private Tournament  tournament;

    @ManyToOne
    @JoinColumn(name = "id_joueur1")
    private User user1;

    @ManyToOne
    @JoinColumn(name = "id_joueur2")
    private User user2;

    private LocalDateTime date_match;
}
