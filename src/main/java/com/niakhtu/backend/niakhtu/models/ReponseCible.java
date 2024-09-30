package com.niakhtu.backend.niakhtu.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "reponse_cible")
public class ReponseCible {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-génération de l'ID
    @Column(name = "REPONSE_ID", nullable = false)
    private Long id;

    // Relation avec Complainte
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "COMPLAINTE_ID", nullable = false)
    private Complainte complainte;

    @NotNull
    @Column(name = "REPONSE_DATE", nullable = false)
    private LocalDate reponseDate;

    @Column(name = "REPONSE_HEURE")
    private LocalTime reponseHeure;

    @Size(max = 255)
    @NotNull
    @Column(name = "REPONSE_EXPOSE", nullable = false)
    private String reponseExpose;

    @Size(max = 255)
    @Column(name = "REPONSE_SUITE")
    private String reponseSuite;

    @NotNull
    @Column(name = "REPONSE_STATUT", nullable = false)
    private Integer reponseStatut;
}
