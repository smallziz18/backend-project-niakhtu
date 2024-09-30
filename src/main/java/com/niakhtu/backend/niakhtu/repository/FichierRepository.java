package com.niakhtu.backend.niakhtu.repository;

import com.niakhtu.backend.niakhtu.models.Fichier;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FichierRepository extends JpaRepository<Fichier, Long> {
}
