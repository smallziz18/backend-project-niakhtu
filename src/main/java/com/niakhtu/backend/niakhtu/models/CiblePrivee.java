package com.niakhtu.backend.niakhtu.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "cible_privee")
public class CiblePrivee {
    @Id
    @Column(name = "CIBLE_ID", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "CIBLE_NOM", nullable = false)
    private String cibleNom;

    @Size(max = 255)
    @Column(name = "CIBLE_TEL1")
    private String cibleTel1;

    @Size(max = 255)
    @Column(name = "CIBLE_TEL2")
    private String cibleTel2;

    @Size(max = 255)
    @Column(name = "CIBLE_ADRESSE")
    private String cibleAdresse;

    @Size(max = 255)
    @Column(name = "CIBLE_EMAIL")
    private String cibleEmail;

    @Size(max = 255)
    @Column(name = "CIBLE_AUTRE")
    private String cibleAutre;

    @Size(max = 255)
    @Column(name = "CIBLE_MOTS_CLES")
    private String cibleMotsCles;

}