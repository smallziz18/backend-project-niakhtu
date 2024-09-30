package com.niakhtu.backend.niakhtu.services;
import com.niakhtu.backend.niakhtu.models.Structure;
import com.niakhtu.backend.niakhtu.repository.StructureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StructureService {

    // Déclaration du repository pour accéder aux données des structures
    private final StructureRepository structureRepository;

    // Injection du repository par le constructeur
    @Autowired
    public StructureService(StructureRepository structureRepository) {
        this.structureRepository = structureRepository;
    }

    // Méthode pour obtenir toutes les structures
    public List<Structure> getAllStructures() {
        return structureRepository.findAll();
    }

    // Méthode pour obtenir une structure par son ID
    public Optional<Structure> getStructureById(Long id) {
        return structureRepository.findById(id);
    }

    // Méthode pour créer une nouvelle structure
    public Structure createStructure(Structure structure) {
        return structureRepository.save(structure);
    }

    // Méthode pour mettre à jour une structure existante
    public Structure updateStructure(Long id, Structure updatedStructure) {
        return structureRepository.findById(id)
                .map(structure -> {
                    structure.setStructureLibelle(updatedStructure.getStructureLibelle());
                    structure.setStructureOrdre(updatedStructure.getStructureOrdre());
                    structure.setStructureEmail(updatedStructure.getStructureEmail());
                    structure.setStructureTel1(updatedStructure.getStructureTel1());
                    structure.setStructureTel2(updatedStructure.getStructureTel2());
                    structure.setStructureMotsCles(updatedStructure.getStructureMotsCles());
                    return structureRepository.save(structure);
                })
                .orElseGet(() -> {
                    updatedStructure.setId(id);
                    return structureRepository.save(updatedStructure);
                });
    }

    // Méthode pour supprimer une structure par son ID
    public void deleteStructure(Long id) {
        structureRepository.deleteById(id);
    }
}

