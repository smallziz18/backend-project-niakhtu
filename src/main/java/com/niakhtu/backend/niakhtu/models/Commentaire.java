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
@Table(name = "commentaires")
public class Commentaire {
    @EmbeddedId
    private CommentaireId id;

    @MapsId("complainteId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "COMPLAINTE_ID", nullable = false)
    private Complainte complainte;

    @NotNull
    @Column(name = "COMMENT_DATE", nullable = false)
    private LocalDate commentDate;

    @Column(name = "COMMENT_HEURE")
    private LocalTime commentHeure;

    @Size(max = 255)
    @NotNull
    @Column(name = "COMMENT_EXPOSE", nullable = false)
    private String commentExpose;

    @Size(max = 255)
    @Column(name = "COMMENT_SUITE")
    private String commentSuite;

    @NotNull
    @Column(name = "COMMENT_STATUT", nullable = false)
    private Integer commentStatut;

    @Size(max = 255)
    @NotNull
    @Column(name = "COMMENT_IP", nullable = false)
    private String commentIp;

    @Column(name = "COMMENT_VOTE")
    private Integer commentVote;

}