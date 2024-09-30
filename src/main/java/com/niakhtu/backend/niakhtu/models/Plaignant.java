package com.niakhtu.backend.niakhtu.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "plaignant")
public class Plaignant {

    @Id
    @Column(name = "PLAIGNANT_ID", nullable = false)
    private String plaignantId = UUID.randomUUID().toString();

    @NotNull
    @Column(name = "PLAIGNANT_PSEUDO", nullable = false, unique = true)
    private String plaignantPseudo;

    @NotNull
    @Column(name = "PLAIGNANT_PRENOM", nullable = false)
    private String plaignantPrenom;

    @NotNull
    @Column(name = "PLAIGNANT_NOM", nullable = false)
    private String plaignantNom;

    @Size(max = 30)
    @NotNull
    @Column(name = "PLAIGNANT_TEL1", nullable = false, length = 13)
    private String plaignantTel1;

    @Size(max = 30)
    @Column(name = "PLAIGNANT_TEL2", length = 13)
    private String plaignantTel2;

    @Size(max = 50)
    @Email
    @Column(name = "PLAIGNANT_EMAIL", length = 50)
    private String plaignantEmail;

    @Column(name = "PLAIGNANT_SEXE")
    @NotNull
    private Integer plaignantSexe;

    @NotNull
    @Column(name = "PLAIGNANT_AGE")
    private Integer plaignantAge;

    @Size(max = 255)
    @Column(name = "PLAIGNANT_AVATAR")
    private String plaignantAvatar;

    @Column(name = "MOT_DE_PASSE", nullable = false)
    @NotNull
    private String motDePasse;

    @Column(name = "IS_VERIFIED", nullable = false)
    private boolean isVerified = false; // Par défaut, non vérifié
}
