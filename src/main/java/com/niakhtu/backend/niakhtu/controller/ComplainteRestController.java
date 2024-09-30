package com.niakhtu.backend.niakhtu.controller;

import com.niakhtu.backend.niakhtu.models.Complainte;
import com.niakhtu.backend.niakhtu.repository.ComplainteRepository;
import com.niakhtu.backend.niakhtu.services.ComplainteService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.Optional;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;
import static org.springframework.util.MimeTypeUtils.APPLICATION_JSON_VALUE;

//page 3
@RestController

public class ComplainteRestController {

    private ComplainteRepository complainteRepository;
    private ComplainteService complainteService;

    //injection de dependance
    public  ComplainteRestController(ComplainteRepository complainteRepository,ComplainteService complainteService ) {
        this.complainteRepository = complainteRepository;
        this.complainteService=complainteService;
    }
    @ResponseStatus(value = HttpStatus.ACCEPTED)
@PostMapping(path = "/ajouter")
    public void saveComplainte(@RequestBody Complainte complainte) {
        this.complainteService.creer(complainte);
    }
    // pour un update
@PostMapping(path = "/updatecomplainte/{id}")
public void updateComplainte(@PathVariable Long id ,@RequestBody Complainte complainte){
    this.complainteService.updateComplainte(complainte );
}
//pour supprimer une complainte a traver son id
@DeleteMapping (path = "/supprimercomplainte/{id}")
    public void deleteComplainte(@PathVariable Long id) {
        complainteService.deleteComplainte(id);
    }

    // recuperatuon de l'ensebles des complaites
    @GetMapping(path = "/complaintes")
    public List<Complainte> getAllComplaintes() {
        return this.complainteService.getAllComplaintes();
    }
    // pour recuperer une complainte a travers son id
    @GetMapping(path = "/complaites/{id}")
    public Optional<Complainte> getComplainteById(@PathVariable  Long id) {
        return this.complainteService.getComplainteById(id);
    }


}
