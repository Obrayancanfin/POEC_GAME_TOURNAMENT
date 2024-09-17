package org.example.game_tournement.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity @Table(name = "user")

public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message="Username is a required field.")
    @Size(min = 1, max = 32, message="Username must be between 1 and 32 characters.")
    private String username;

    private String password;

    @Builder.Default
    private String roles = "USER";

    @Transient
    private String repeatedPassword;

    private String avatarPath; // En partant du principe que l'on stockera les avatars hors BDD pour l'instant (en local dans l'appli)

}
