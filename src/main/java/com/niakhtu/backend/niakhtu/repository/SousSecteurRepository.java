package com.niakhtu.backend.niakhtu.repository;



import com.niakhtu.backend.niakhtu.models.SousSecteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SousSecteurRepository extends JpaRepository<SousSecteur, Long> {
    // Méthodes de requêtes personnalisées si besoin
}

