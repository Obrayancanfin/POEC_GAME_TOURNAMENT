package org.example.game_tournement.Entity;

import jakarta.persistence.*;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity @Table(name = "user")

public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String username;
    private String password;
    private String avatarPath; // En partant du principe que l'on stockera les avatars hors BDD pour l'instant (en local dans l'appli)
}
