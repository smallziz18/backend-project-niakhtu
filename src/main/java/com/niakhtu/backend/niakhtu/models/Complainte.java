package com.niakhtu.backend.niakhtu.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "complainte")
public class Complainte {
    @Id
    @Column(name = "COMPLAINTE_ID", nullable = false)
    private Long id;

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

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TYPE_PLAINTE_CODE", nullable = false)
    private TypePlainte typePlainteCode;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "TYPE_CIBLE_CODE", nullable = false)
    private TypeCible typeCibleCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CIBLE_ID")
    private CiblePrivee cible;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "STRUCTURE_ID", nullable = false)
    private Structure structure;

    @OneToMany(mappedBy = "complainte")
    private Set<Commentaire> commentaires = new LinkedHashSet<>();

    @OneToMany(mappedBy = "complainte")
    private Set<ReponseCible> reponseCibles = new LinkedHashSet<>();

}