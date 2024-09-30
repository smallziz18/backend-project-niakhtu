package com.niakhtu.backend.niakhtu.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "structure")
public class Structure {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-génération de l'ID
    @Column(name = "STRUCTURE_ID", nullable = false)
    private Long id;

    @Size(max = 255)
    @NotNull
    @Column(name = "STRUCTURE_LIBELLE", nullable = false)
    private String structureLibelle;

    @Column(name = "STRUCTURE_ORDRE")
    private Long structureOrdre;

    @Size(max = 255)
    @Column(name = "STRUCTURE_EMAIL")
    private String structureEmail;

    @Size(max = 255)
    @Column(name = "STRUCTURE_TEL1")
    private String structureTel1;

    @Size(max = 255)
    @Column(name = "STRUCTURE_TEL2")
    private String structureTel2;

    @Size(max = 255)
    @Column(name = "STRUCTURE_MOTS_CLES")
    private String structureMotsCles;
}
