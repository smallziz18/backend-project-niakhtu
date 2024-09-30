package com.niakhtu.backend.niakhtu.services;



import com.niakhtu.backend.niakhtu.execptions.CiblePriveeNotFoundException;
import com.niakhtu.backend.niakhtu.models.CiblePrivee;
import com.niakhtu.backend.niakhtu.repository.CiblePriveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CiblePriveeService {

    private final CiblePriveRepository ciblePriveRepository;

    @Autowired
    public CiblePriveeService(CiblePriveRepository ciblePriveRepository) {
        this.ciblePriveRepository = ciblePriveRepository;
    }

    // Retourner l'ensemble des cibles privées
    public List<CiblePrivee> getAllCiblePrivees() {
        return ciblePriveRepository.findAll();
    }

    // Trouver une cible privée à partir de son identifiant
    public Optional<CiblePrivee> getCiblePriveeById(long id) {
        return Optional.ofNullable(ciblePriveRepository.findById(id)
                .orElseThrow(() -> new CiblePriveeNotFoundException(id)));
    }

    // Créer une nouvelle cible privée
    public CiblePrivee createCiblePrivee(CiblePrivee ciblePrivee) {
        return ciblePriveRepository.save(ciblePrivee);
    }

    // Mettre à jour une cible privée existante
    public CiblePrivee updateCiblePrivee(long id, CiblePrivee cibleDetails) {
        CiblePrivee cible = ciblePriveRepository.findById(id)
                .orElseThrow(() -> new CiblePriveeNotFoundException(id));

        cible.setCibleNom(cibleDetails.getCibleNom());
        cible.setCibleTel1(cibleDetails.getCibleTel1());
        cible.setCibleTel2(cibleDetails.getCibleTel2());
        cible.setCibleAdresse(cibleDetails.getCibleAdresse());
        cible.setCibleEmail(cibleDetails.getCibleEmail());
        cible.setCibleAutre(cibleDetails.getCibleAutre());
        cible.setCibleMotsCles(cibleDetails.getCibleMotsCles());

        return ciblePriveRepository.save(cible);
    }

    // Supprimer une cible privée
    public void deleteCiblePrivee(long id) {
        CiblePrivee cible = ciblePriveRepository.findById(id)
                .orElseThrow(() -> new CiblePriveeNotFoundException(id));

        ciblePriveRepository.delete(cible);
    }
}
