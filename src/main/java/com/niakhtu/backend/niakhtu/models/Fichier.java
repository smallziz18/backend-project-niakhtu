package com.niakhtu.backend.niakhtu.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "fichier")
public class Fichier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Utilisation de l'auto-incrémentation pour l'ID
    @Column(name = "FIC_ID", nullable = false)
    private Long id;

    // Relation avec l'entité Complainte (clé étrangère)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "COMPLAINTE_ID")
    private Complainte complainte;

    @Size(max = 255)
    @NotNull
    @Column(name = "FIC_NOM", nullable = false) // Nom du fichier
    private String ficNom;

    @Size(max = 255)
    @NotNull
    @Column(name = "FIC_CHEMIN", nullable = false) // Chemin du fichier sur le serveur
    private String ficChemin;

    @NotNull
    @Column(name = "FIC_DATE_HEURE", nullable = false) // Date et heure de stockage
    private Instant ficDateHeure;

    @NotNull
    @Column(name = "FIC_TYPE", nullable = false) // Type du fichier (image, audio, vidéo)
    private Integer ficType;

    @NotNull
    @Column(name = "FIC_AUTEUR", nullable = false) // Clé étrangère pour l'auteur du fichier
    private String ficAuteur;

    @Column(name = "FIC_ORDRE") // Ordre des fichiers si nécessaire
    private Integer ficOrdre;

    @Size(max = 255)
    @Column(name = "FIC_COMMENT") // Commentaire facultatif sur le fichier
    private String ficComment;

    public void setComplainteId(Long complainteId) {
        this.complainte = new Complainte(); // Crée une nouvelle instance de Complainte
        this.complainte.setId(complainteId); // Définit l'ID de la plainte
    }


}
