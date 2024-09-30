package com.niakhtu.backend.niakhtu.services;
import com.niakhtu.backend.niakhtu.models.SousSecteur;
import com.niakhtu.backend.niakhtu.repository.SousSecteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SousSecteurService {

    // Déclaration du repository pour accéder aux données des sous-secteurs
    private final SousSecteurRepository sousSecteurRepository;

    // Injection du repository par le constructeur
    @Autowired
    public SousSecteurService(SousSecteurRepository sousSecteurRepository) {
        this.sousSecteurRepository = sousSecteurRepository;
    }

    // Méthode pour obtenir tous les sous-secteurs
    public List<SousSecteur> getAllSousSecteurs() {
        return sousSecteurRepository.findAll();
    }

    // Méthode pour obtenir un sous-secteur par son ID
    public Optional<SousSecteur> getSousSecteurById(Long id) {
        return sousSecteurRepository.findById(id);
    }

    // Méthode pour créer un nouveau sous-secteur
    public SousSecteur createSousSecteur(SousSecteur sousSecteur) {
        return sousSecteurRepository.save(sousSecteur);
    }

    // Méthode pour mettre à jour un sous-secteur existant
    public SousSecteur updateSousSecteur(Long id, SousSecteur updatedSousSecteur) {
        return sousSecteurRepository.findById(id)
                .map(sousSecteur -> {
                    sousSecteur.setSsecteurLibelle(updatedSousSecteur.getSsecteurLibelle());
                    sousSecteur.setSsecteurOrdre(updatedSousSecteur.getSsecteurOrdre());
                    sousSecteur.setSsecteurEmail(updatedSousSecteur.getSsecteurEmail());
                    sousSecteur.setSsecteurTel1(updatedSousSecteur.getSsecteurTel1());
                    sousSecteur.setSsecteurTel2(updatedSousSecteur.getSsecteurTel2());
                    sousSecteur.setSsecteurMotsCles(updatedSousSecteur.getSsecteurMotsCles());
                    return sousSecteurRepository.save(sousSecteur);
                })
                .orElseGet(() -> {
                    updatedSousSecteur.setId(id);
                    return sousSecteurRepository.save(updatedSousSecteur);
                });
    }

    // Méthode pour supprimer un sous-secteur par son ID
    public void deleteSousSecteur(Long id) {
        sousSecteurRepository.deleteById(id);
    }
}

