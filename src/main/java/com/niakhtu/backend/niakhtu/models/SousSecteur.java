package com.niakhtu.backend.niakhtu.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "sous_secteur")
public class SousSecteur {
    @Id
    @Column(name = "SSECTEUR_ID", nullable = false)
    private Long id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "SECTEUR_ID", nullable = false)
    private Secteur secteur;

    @Size(max = 255)
    @NotNull
    @Column(name = "SSECTEUR_LIBELLE", nullable = false)
    private String ssecteurLibelle;

    @Column(name = "SSECTEUR_ORDRE")
    private Long ssecteurOrdre;

    @Size(max = 255)
    @Column(name = "SSECTEUR_EMAIL")
    private String ssecteurEmail;

    @Size(max = 255)
    @Column(name = "SSECTEUR_TEL1")
    private String ssecteurTel1;

    @Size(max = 255)
    @Column(name = "SSECTEUR_TEL2")
    private String ssecteurTel2;

    @Size(max = 255)
    @Column(name = "SSECTEUR_MOTS_CLES")
    private String ssecteurMotsCles;

}