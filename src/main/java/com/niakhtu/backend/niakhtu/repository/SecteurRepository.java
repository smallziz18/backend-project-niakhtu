package com.niakhtu.backend.niakhtu.repository;



import com.niakhtu.backend.niakhtu.models.Secteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecteurRepository extends JpaRepository<Secteur, Long> {
    // Vous pouvez ajouter des méthodes personnalisées si nécessaire
}

