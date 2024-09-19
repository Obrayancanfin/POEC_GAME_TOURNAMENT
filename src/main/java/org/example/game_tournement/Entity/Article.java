package org.example.game_tournement.Entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "article")
public class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Ce champ doit être rempli !")
    private String title;
    @NotBlank(message = "Ce champ doit être rempli !")
    private String category;
    @NotBlank(message = "Ce champ doit être rempli !")
    private String content;
}
