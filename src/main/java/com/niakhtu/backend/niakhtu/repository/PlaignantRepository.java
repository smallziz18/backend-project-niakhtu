package com.niakhtu.backend.niakhtu.repository;


import com.niakhtu.backend.niakhtu.models.Plaignant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PlaignantRepository extends JpaRepository<Plaignant, String> {

    // Recherche d'un plaignant par son pseudo
    Optional<Plaignant> findPlaignantByPlaignantPseudo(String pseudo);

    // Recherche d'un plaignant par son email
    Optional<Plaignant> findByPlaignantEmail(String email);

    // Recherche d'un plaignant par son numéro de téléphone principal
    Optional<Plaignant> findByPlaignantTel1(String tel1);

    // Méthode pour trouver un plaignant par pseudo
    Optional<Plaignant> findByPlaignantPseudo(String plaignantPseudo);
    // Recherche d'un plaignant par son pseudo
   ;


}
