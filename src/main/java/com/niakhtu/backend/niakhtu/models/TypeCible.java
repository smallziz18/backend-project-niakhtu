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
@Table(name = "type_cible")
public class TypeCible {
    @Id
    @Size(max = 50)
    @Column(name = "TYPE_CIBLE_CODE", nullable = false, length = 50)
    private String typeCibleCode;

    @Size(max = 255)
    @NotNull
    @Column(name = "TYPE_CIBLE_LIBELLE", nullable = false)
    private String typeCibleLibelle;

    @Column(name = "TYPE_CIBLE_ORDRE")
    private Long typeCibleOrdre;

}