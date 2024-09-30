package com.niakhtu.backend.niakhtu.controller;

import com.niakhtu.backend.niakhtu.models.LoginRequest;
import com.niakhtu.backend.niakhtu.models.Plaignant;
import com.niakhtu.backend.niakhtu.services.PlaignantService;
import com.niakhtu.backend.niakhtu.services.UserVerificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/plaignants")
public class PlaignantController {

    private final PlaignantService plaignantService;
    private final UserVerificationService userVerificationService;

    public PlaignantController(PlaignantService plaignantService, UserVerificationService userVerificationService) {
        this.plaignantService = plaignantService;
        this.userVerificationService = userVerificationService;
    }

    // Endpoint pour inscrire un nouvel utilisateur avec un avatar
    @PostMapping("/register")
    public ResponseEntity<String> registerPlaignant(@RequestPart("plaignant") Plaignant plaignant, @RequestPart("avatar") MultipartFile avatarFile) {
        try {
            // Vérifier si le plaignant existe déjà
            if (plaignantService.existsByPseudo(plaignant.getPlaignantPseudo())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Un utilisateur avec ce pseudo existe déjà.");
            }

            // Sauvegarde du plaignant avec avatar
            plaignantService.savePlaignant(plaignant, avatarFile);

            // Envoi du code de vérification par SMS et par email
            userVerificationService.sendVerificationCode(plaignant.getPlaignantId(), plaignant.getPlaignantTel1(), plaignant.getPlaignantEmail());

            return ResponseEntity.ok("Un code de vérification a été envoyé par SMS et par email.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de l'enregistrement du plaignant : " + e.getMessage());
        }
    }

    // Récupérer tous les plaignants
    @GetMapping
    public List<Plaignant> getAllPlaignants() {
        return plaignantService.findAllPlaignants();
    }

    // Récupérer un plaignant par ID
    @GetMapping("/{id}")
    public ResponseEntity<Plaignant> getPlaignantById(@PathVariable String id) {
        return plaignantService.findPlaignantById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).body(null));
    }

    // Mettre à jour un plaignant par ID
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updatePlaignant(
            @PathVariable String id,
            @RequestPart("plaignant") Plaignant updatedPlaignant,
            @RequestPart("avatar") MultipartFile avatarFile) {
        try {
            Plaignant updated = plaignantService.updatePlaignant(id, updatedPlaignant, avatarFile);
            return ResponseEntity.ok("Plaignant mis à jour avec succès.");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors de la mise à jour : " + e.getMessage());
        }
    }

    // Supprimer un plaignant par ID
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deletePlaignant(@PathVariable String id) {
        plaignantService.deletePlaignant(id);
        return ResponseEntity.noContent().build();
    }

    // Endpoint pour vérifier le code de vérification
    @PostMapping("/verify")
    public ResponseEntity<String> verifyPlaignant(@RequestParam String plaignantId, @RequestParam String code) {
        boolean isVerified = userVerificationService.verifyCode(plaignantId, code);
        if (isVerified) {
            Optional<Plaignant> plaignantOptional = plaignantService.findPlaignantById(plaignantId);
            if (plaignantOptional.isPresent()) {
                Plaignant plaignant = plaignantOptional.get();
                plaignant.setVerified(true);
                try {
                    plaignantService.savePlaignant(plaignant, null); // Sauvegarder le statut vérifié sans modifier l'avatar
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                return ResponseEntity.ok("Vérification réussie.");
            }
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Utilisateur non trouvé.");
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Code de vérification incorrect ou expiré.");
        }
    }

    // Endpoint pour la connexion de l'utilisateur (login)
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        Optional<Plaignant> plaignantOptional = plaignantService.findPlaignantByPseudo(loginRequest.getPseudo());

        if (plaignantOptional.isEmpty()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Pseudo ou mot de passe incorrect.");
        }

        Plaignant plaignant = plaignantOptional.get();

        if (!plaignant.isVerified()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("Veuillez vérifier votre compte avant de vous connecter.");
        }

        boolean isAuthenticated = plaignantService.authenticatePlaignant(loginRequest.getPseudo(), loginRequest.getPassword());
        if (isAuthenticated) {
            return ResponseEntity.ok("Connexion réussie");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Pseudo ou mot de passe incorrect");
        }
    }

    // Endpoint pour ajouter ou mettre à jour un avatar
    @PostMapping("/{id}/avatar")
    public ResponseEntity<String> addOrUpdateAvatar(@PathVariable String id, @RequestParam("avatar") MultipartFile avatarFile) {
        try {
            String avatarFileName = plaignantService.addOrUpdateAvatar(id, avatarFile);
            if (avatarFileName != null) {
                return ResponseEntity.ok("Avatar mis à jour avec succès : " + avatarFileName);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Plaignant non trouvé.");
            }
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erreur lors du téléchargement de l'avatar : " + e.getMessage());
        }
    }
}
