package com.niakhtu.backend.niakhtu.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "complainte")
public class Complainte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "COMPLAINTE_ID", nullable = false)
    private Long id;

    // Relation avec l'entité Plaignant
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "PLAIGNANT_ID", nullable = false)
    private Plaignant plaignant;

    @NotNull
    @Column(name = "COMPLAINTE_DATE_RECU", nullable = false)
    private LocalDate complainteDateRecu;

    @NotNull
    @Column(name = "COMPLAINTE_HEURE_RECU", nullable = false)
    private LocalTime complainteHeureRecu;

    @Column(name = "COMPLAINTE_DATE_TRANSFERT")
    private Instant complainteDateTransfert;

    @Size(max = 255)
    @NotNull
    @Column(name = "COMPLAINTE_EXPOSE", nullable = false)
    private String complainteExpose;

    @Size(max = 255)
    @Column(name = "COMPLAINTE_SUITE")
    private String complainteSuite;

    @Size(max = 255)
    @Column(name = "COMPLAINTE_LOCALISE_LONG")
    private String complainteLocaliseLong;

    @Size(max = 255)
    @Column(name = "COMPLAINTE_LOCALISE_LAT")
    private String complainteLocaliseLat;

    @Size(max = 255)
    @Column(name = "COMPLAINTE_LOCALISE_ALT")
    private String complainteLocaliseAlt;

    @Size(max = 255)
    @Column(name = "COMPLAINTE_LOCALISE_AUT")
    private String complainteLocaliseAut;

    @Column(name = "COMPLAINTE_STATUT")
    private Integer complainteStatut;

    @Size(max = 255)
    @Column(name = "COMPLAINTE_CAUSE_NR")
    private String complainteCauseNr;

    // Relation avec l'entité TypePlainte
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TYPE_PLAINTE_CODE", nullable = false)
    private TypePlainte typePlainteCode;

    // Relation avec l'entité TypeCible
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TYPE_CIBLE_CODE", nullable = false)
    private TypeCible typeCibleCode;

    // Relation avec l'entité CiblePrivee
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CIBLE_ID")
    private CiblePrivee cible;

    // Relation avec l'entité Structure
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STRUCTURE_ID", nullable = false)
    private Structure structure;

    // Relation avec l'entité Commentaire (One-to-Many)
    @OneToMany(mappedBy = "complainte", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Commentaire> commentaires = new LinkedHashSet<>();

    // Relation avec l'entité Fichier (One-to-Many)
    @OneToMany(mappedBy = "complainte", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Fichier> fichier = new LinkedHashSet<>();

    // Relation avec l'entité ReponseCible (One-to-Many)
    @OneToMany(mappedBy = "complainte", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReponseCible> reponseCibles = new LinkedHashSet<>();
}
