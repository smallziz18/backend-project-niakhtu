package com.niakhtu.backend.niakhtu.services;
import com.niakhtu.backend.niakhtu.models.Secteur;
import com.niakhtu.backend.niakhtu.repository.SecteurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SecteurService {

    // Déclaration du repository pour accéder aux données des secteurs
    private final SecteurRepository secteurRepository;

    // Injection du repository par le constructeur
    @Autowired
    public SecteurService(SecteurRepository secteurRepository) {
        this.secteurRepository = secteurRepository;
    }

    // Méthode pour obtenir tous les secteurs
    public List<Secteur> getAllSecteurs() {
        return secteurRepository.findAll();
    }

    // Méthode pour obtenir un secteur par son ID
    public Optional<Secteur> getSecteurById(Long id) {
        return secteurRepository.findById(id);
    }

    // Méthode pour créer un nouveau secteur
    public Secteur createSecteur(Secteur secteur) {
        return secteurRepository.save(secteur);
    }

    // Méthode pour mettre à jour un secteur existant
    public Secteur updateSecteur(Long id, Secteur updatedSecteur) {
        return secteurRepository.findById(id)
                .map(secteur -> {
                    secteur.setSecteurLibelle(updatedSecteur.getSecteurLibelle());
                    secteur.setSecteurOrdre(updatedSecteur.getSecteurOrdre());
                    secteur.setSecteurEmail(updatedSecteur.getSecteurEmail());
                    secteur.setSecteurTel1(updatedSecteur.getSecteurTel1());
                    secteur.setSecteurTel2(updatedSecteur.getSecteurTel2());
                    secteur.setSecteurMotsCles(updatedSecteur.getSecteurMotsCles());
                    return secteurRepository.save(secteur);
                })
                .orElseGet(() -> {
                    updatedSecteur.setId(id);
                    return secteurRepository.save(updatedSecteur);
                });
    }

    // Méthode pour supprimer un secteur par son ID
    public void deleteSecteur(Long id) {
        secteurRepository.deleteById(id);
    }
}

