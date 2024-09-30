package com.niakhtu.backend.niakhtu.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;

import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode // Pour gérer correctement l'égalité des objets Secteur
@Entity
@Table(name = "secteur")
public class Secteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-génération de l'ID
    @Column(name = "SECTEUR_ID", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "SECTEUR_LIBELLE", nullable = false)
    private String secteurLibelle;

    @Column(name = "SECTEUR_ORDRE")
    private Long secteurOrdre;

    @Size(max = 255)
    @Column(name = "SECTEUR_EMAIL")
    private String secteurEmail;

    @Size(max = 255)
    @Column(name = "SECTEUR_TEL1")
    private String secteurTel1;

    @Size(max = 255)
    @Column(name = "SECTEUR_TEL2")
    private String secteurTel2;

    @Size(max = 255)
    @Column(name = "SECTEUR_MOTS_CLES")
    private String secteurMotsCles;

    // Relation avec SousSecteur
    @OneToMany(mappedBy = "secteur", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<SousSecteur> sousSecteurs = new LinkedHashSet<>();
}
