package com.niakhtu.backend.niakhtu.dtos;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
//Ce package contient les DTOs, souvent utilisés pour les échanges de données
// entre le frontend (Flutter) et le backend.

public class ComplainteDTO {
    private Long id;
    private String complainteExpose;
    private LocalDate complainteDateRecu;
    private LocalTime complainteHeureRecu;

    // Ajoutez d'autres champs pertinents

    // Getters et setters
}
