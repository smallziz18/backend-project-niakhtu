package com.niakhtu.backend.niakhtu.execptions;



public class CiblePriveeNotFoundException extends RuntimeException {
    public CiblePriveeNotFoundException(Long id) {
        super("Cible privée avec ID " + id + " non trouvée.");
    }
}

