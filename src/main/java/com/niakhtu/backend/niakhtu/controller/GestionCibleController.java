package com.niakhtu.backend.niakhtu.controller;


import com.niakhtu.backend.niakhtu.execptions.CiblePriveeNotFoundException;
import com.niakhtu.backend.niakhtu.models.CiblePrivee;
import com.niakhtu.backend.niakhtu.models.Secteur;
import com.niakhtu.backend.niakhtu.models.SousSecteur;
import com.niakhtu.backend.niakhtu.models.Structure;
import com.niakhtu.backend.niakhtu.services.CiblePriveeService;
import com.niakhtu.backend.niakhtu.services.SecteurService;
import com.niakhtu.backend.niakhtu.services.SousSecteurService;
import com.niakhtu.backend.niakhtu.services.StructureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class GestionCibleController {

    private final StructureService structureService;
    private final SousSecteurService sousSecteurService;
    private final SecteurService secteurService;
    private final CiblePriveeService ciblePriveeService;
    @Autowired
    public GestionCibleController(StructureService structureService, SousSecteurService sousSecteurService, SecteurService secteurService, CiblePriveeService ciblePriveeService){
        this.structureService = structureService;
        this.secteurService= secteurService;
        this.sousSecteurService=sousSecteurService;
        this.ciblePriveeService=ciblePriveeService;
    }

    // ********** Endpoints pour Structure **********

    @GetMapping("/structures")
    public List<Structure> getAllStructures() {
        return structureService.getAllStructures();
    }

    @GetMapping("/structures/{id}")
    public ResponseEntity<Structure> getStructureById(@PathVariable Long id) {
        Optional<Structure> structure = structureService.getStructureById(id);
        return structure.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/structures")
    public Structure createStructure(@RequestBody Structure structure) {
        return structureService.createStructure(structure);
    }

    @PutMapping("/structures/{id}")
    public ResponseEntity<Structure> updateStructure(@PathVariable Long id, @RequestBody Structure updatedStructure) {
        Structure structure = structureService.updateStructure(id, updatedStructure);
        return ResponseEntity.ok(structure);
    }

    @DeleteMapping("/structures/{id}")
    public ResponseEntity<Void> deleteStructure(@PathVariable Long id) {
        structureService.deleteStructure(id);
        return ResponseEntity.noContent().build();
    }

    // ********** Endpoints pour SousSecteur **********

    @GetMapping("/sous-secteurs")
    public List<SousSecteur> getAllSousSecteurs() {
        return sousSecteurService.getAllSousSecteurs();
    }

    @GetMapping("/sous-secteurs/{id}")
    public ResponseEntity<SousSecteur> getSousSecteurById(@PathVariable Long id) {
        Optional<SousSecteur> sousSecteur = sousSecteurService.getSousSecteurById(id);
        return sousSecteur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/sous-secteurs")
    public SousSecteur createSousSecteur(@RequestBody SousSecteur sousSecteur) {
        return sousSecteurService.createSousSecteur(sousSecteur);
    }

    @PutMapping("/sous-secteurs/{id}")
    public ResponseEntity<SousSecteur> updateSousSecteur(@PathVariable Long id, @RequestBody SousSecteur updatedSousSecteur) {
        SousSecteur sousSecteur = sousSecteurService.updateSousSecteur(id, updatedSousSecteur);
        return ResponseEntity.ok(sousSecteur);
    }

    @DeleteMapping("/sous-secteurs/{id}")
    public ResponseEntity<Void> deleteSousSecteur(@PathVariable Long id) {
        sousSecteurService.deleteSousSecteur(id);
        return ResponseEntity.noContent().build();
    }

    // ********** Endpoints pour Secteur **********

    @GetMapping("/secteurs")
    public List<Secteur> getAllSecteurs() {
        return secteurService.getAllSecteurs();
    }

    @GetMapping("/secteurs/{id}")
    public ResponseEntity<Secteur> getSecteurById(@PathVariable Long id) {
        Optional<Secteur> secteur = secteurService.getSecteurById(id);
        return secteur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("/secteurs")
    public Secteur createSecteur(@RequestBody Secteur secteur) {
        return secteurService.createSecteur(secteur);
    }

    @PutMapping("/secteurs/{id}")
    public ResponseEntity<Secteur> updateSecteur(@PathVariable Long id, @RequestBody Secteur updatedSecteur) {
        Secteur secteur = secteurService.updateSecteur(id, updatedSecteur);
        return ResponseEntity.ok(secteur);
    }

    @DeleteMapping("/secteurs/{id}")
    public ResponseEntity<Void> deleteSecteur(@PathVariable Long id) {
        secteurService.deleteSecteur(id);
        return ResponseEntity.noContent().build();
    }

    // ************ Endpoint pour obtenir toutes les cibles privées ****************
    @GetMapping(path = "/cibleprivees")
    public List<CiblePrivee> getAllCiblePrivees() {
        return ciblePriveeService.getAllCiblePrivees();
    }

    // ************ Endpoint pour obtenir une cible privée par ID ****************
    @GetMapping("cible/{id}")
    public ResponseEntity<CiblePrivee> getCiblePriveeById(@PathVariable long id) {
        CiblePrivee ciblePrivee = ciblePriveeService.getCiblePriveeById(id)
                .orElseThrow(() -> new CiblePriveeNotFoundException(id));
        return ResponseEntity.ok(ciblePrivee);
    }

    // ************ Endpoint pour créer une nouvelle cible privée ****************
    @PostMapping(path = "/nouvellecible")
    public ResponseEntity<CiblePrivee> createCiblePrivee(@RequestBody CiblePrivee ciblePrivee) {
        CiblePrivee newCible = ciblePriveeService.createCiblePrivee(ciblePrivee);
        return new ResponseEntity<>(newCible, HttpStatus.CREATED);
    }

    // ************ Endpoint pour mettre à jour une cible privée ****************
    @PutMapping(path = "upadte/{id}")
    public ResponseEntity<CiblePrivee> updateCiblePrivee(
            @PathVariable long id,
            @RequestBody CiblePrivee cibleDetails) {
        CiblePrivee updatedCible = ciblePriveeService.updateCiblePrivee(id, cibleDetails);
        return ResponseEntity.ok(updatedCible);
    }

    // ************ Endpoint pour supprimer une cible privée ****************
    @DeleteMapping(path = "supprimercible/{id}")
    public ResponseEntity<Void> deleteCiblePrivee(@PathVariable long id) {
        ciblePriveeService.deleteCiblePrivee(id);
        return ResponseEntity.noContent().build();
    }
}
