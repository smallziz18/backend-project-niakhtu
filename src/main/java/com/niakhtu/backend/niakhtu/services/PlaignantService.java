package com.niakhtu.backend.niakhtu.services;

import com.niakhtu.backend.niakhtu.models.Plaignant;
import com.niakhtu.backend.niakhtu.repository.PlaignantRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class PlaignantService {

    private final PlaignantRepository plaignantRepository;
    private final FileStorageService fileStorageService; // Injection du service de gestion de fichiers

    public PlaignantService(PlaignantRepository plaignantRepository, FileStorageService fileStorageService) {
        this.plaignantRepository = plaignantRepository;
        this.fileStorageService = fileStorageService;
    }

    // Vérifier si un utilisateur existe par pseudo
    public boolean existsByPseudo(String pseudo) {
        return plaignantRepository.findPlaignantByPlaignantPseudo(pseudo).isPresent();
    }

    // Enregistrer un nouveau plaignant
    public Plaignant savePlaignant(Plaignant plaignant, MultipartFile avatarFile) throws IOException {
        // Vérifier et stocker l'avatar si fourni
        if (avatarFile != null && !avatarFile.isEmpty()) {
            String avatarFileName = fileStorageService.storeFile(avatarFile, plaignant.getPlaignantId(), null).getFicNom();
            plaignant.setPlaignantAvatar(avatarFileName); // Assigner l'avatar au plaignant
        }

        // Hashage du mot de passe avant de sauvegarder (à adapter selon votre logique de hashage)
        plaignant.setMotDePasse(plaignant.getMotDePasse());

        return plaignantRepository.save(plaignant);
    }

    // Récupérer tous les plaignants
    public List<Plaignant> findAllPlaignants() {
        return plaignantRepository.findAll();
    }

    // Récupérer un plaignant par ID
    public Optional<Plaignant> findPlaignantById(String id) {
        return plaignantRepository.findById(id);
    }

    // Récupérer un plaignant par pseudo
    public Optional<Plaignant> findPlaignantByPseudo(String pseudo) {
        return plaignantRepository.findPlaignantByPlaignantPseudo(pseudo);
    }

    // Mettre à jour un plaignant par ID, y compris l'avatar
    public Plaignant updatePlaignant(String id, Plaignant updatedPlaignant, MultipartFile avatarFile) throws IOException {
        return plaignantRepository.findById(id)
                .map(plaignant -> {
                    plaignant.setPlaignantPseudo(updatedPlaignant.getPlaignantPseudo());
                    plaignant.setPlaignantPrenom(updatedPlaignant.getPlaignantPrenom());
                    plaignant.setPlaignantNom(updatedPlaignant.getPlaignantNom());
                    plaignant.setPlaignantTel1(updatedPlaignant.getPlaignantTel1());
                    plaignant.setPlaignantTel2(updatedPlaignant.getPlaignantTel2());
                    plaignant.setPlaignantEmail(updatedPlaignant.getPlaignantEmail());
                    plaignant.setPlaignantSexe(updatedPlaignant.getPlaignantSexe());
                    plaignant.setPlaignantAge(updatedPlaignant.getPlaignantAge());

                    // Si un avatar est fourni, on le met à jour
                    if (avatarFile != null && !avatarFile.isEmpty()) {
                        try {
                            String avatarFileName = fileStorageService.storeFile(avatarFile, plaignant.getPlaignantId(), null).getFicNom();
                            plaignant.setPlaignantAvatar(avatarFileName);
                        } catch (IOException e) {
                            throw new RuntimeException("Erreur lors du stockage de l'avatar.", e);
                        }
                    }

                    plaignant.setMotDePasse(updatedPlaignant.getMotDePasse()); // Hashage du mot de passe

                    return plaignantRepository.save(plaignant);
                })
                .orElseGet(() -> {
                    // Enregistrer un nouveau plaignant s'il n'existe pas
                    if (avatarFile != null && !avatarFile.isEmpty()) {
                        try {
                            String avatarFileName = fileStorageService.storeFile(avatarFile, updatedPlaignant.getPlaignantId(), null).getFicNom();
                            updatedPlaignant.setPlaignantAvatar(avatarFileName);
                        } catch (IOException e) {
                            throw new RuntimeException("Erreur lors du stockage de l'avatar.", e);
                        }
                    }

                    updatedPlaignant.setMotDePasse(updatedPlaignant.getMotDePasse());
                    return plaignantRepository.save(updatedPlaignant);
                });
    }

    // Supprimer un plaignant par ID
    public void deletePlaignant(String id) {
        plaignantRepository.deleteById(id);
    }

    // Authentifier un utilisateur
    public boolean authenticatePlaignant(String pseudo, String password) {
        Optional<Plaignant> plaignantOptional = findPlaignantByPseudo(pseudo);
        return plaignantOptional.isPresent() && password.equals(plaignantOptional.get().getMotDePasse());
    }

    // Modifier le mot de passe en passant l'ancien mot de passe et le nouveau
    public String updatePassword(String pseudo, String oldPassword, String newPassword) {
        Optional<Plaignant> plaignantOptional = plaignantRepository.findPlaignantByPlaignantPseudo(pseudo);
        if (plaignantOptional.isPresent()) {
            Plaignant plaignant = plaignantOptional.get();
            if (oldPassword.equals(plaignant.getMotDePasse())) {
                plaignant.setMotDePasse(newPassword); // Mise à jour du mot de passe
                plaignantRepository.save(plaignant);
                return "Le mot de passe a été mis à jour avec succès.";
            } else {
                return "L'ancien mot de passe est incorrect.";
            }
        }
        return "Utilisateur non trouvé.";
    }

    // Ajouter ou mettre à jour un avatar
    public String addOrUpdateAvatar(String plaignantId, MultipartFile avatarFile) throws IOException {
        Optional<Plaignant> plaignantOptional = findPlaignantById(plaignantId);
        if (plaignantOptional.isPresent()) {
            Plaignant plaignant = plaignantOptional.get();

            // Si un fichier d'avatar est fourni, on procède au stockage
            if (avatarFile != null && !avatarFile.isEmpty()) {
                String avatarFileName = fileStorageService.storeFile(avatarFile, plaignantId, null).getFicNom(); // Stocker l'avatar
                plaignant.setPlaignantAvatar(avatarFileName); // Mettre à jour l'avatar
            }

            plaignantRepository.save(plaignant); // Enregistrer les modifications du plaignant
            return plaignant.getPlaignantAvatar(); // Retourner l'avatar existant ou mis à jour
        }
        return null; // Si le plaignant n'est pas trouvé
    }
}
