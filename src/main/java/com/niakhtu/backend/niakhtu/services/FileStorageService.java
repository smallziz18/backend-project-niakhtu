package com.niakhtu.backend.niakhtu.services;
import com.niakhtu.backend.niakhtu.models.Fichier;
import com.niakhtu.backend.niakhtu.repository.FichierRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.*;
import java.time.Instant;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;
    private final FichierRepository fichierRepository;

    public FileStorageService(FichierRepository fichierRepository) throws IOException {
        this.fichierRepository = fichierRepository;
        this.fileStorageLocation = Paths.get("uploads").toAbsolutePath().normalize();
        Files.createDirectories(this.fileStorageLocation); // Crée le répertoire si inexistant
    }

    // Méthode pour stocker un fichier et ses informations dans la base de données
    public Fichier storeFile(MultipartFile file, String auteurId, Long complainteId) throws IOException {
        if (file == null || file.isEmpty()) {
            throw new IOException("Le fichier est vide ou non fourni.");
        }

        String fileName = file.getOriginalFilename();

        // Vérifier si le fichier a un nom valide
        if (fileName != null && fileName.contains("..")) {
            throw new IOException("Nom de fichier invalide " + fileName);
        }

        // Stocker le fichier sur le disque
        Path targetLocation = this.fileStorageLocation.resolve(fileName);
        Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

        // Créer une entrée dans la base de données pour ce fichier
        Fichier fichier = new Fichier();

        if (complainteId != null) {
            fichier.setComplainteId(complainteId);  // Associer à la plainte si complainteId est présent
        }

        fichier.setFicNom(fileName);            // Nom du fichier
        fichier.setFicChemin(targetLocation.toString());  // Chemin où le fichier est stocké
        fichier.setFicDateHeure(Instant.now());  // Date d'ajout
        fichier.setFicType(detectFileType(file)); // Type du fichier (image, vidéo, etc.)
        fichier.setFicAuteur(auteurId);          // ID de l'auteur (plaignant)

        // Enregistrer l'objet `Fichier` dans la base de données
        return fichierRepository.save(fichier);
    }

    // Méthode pour récupérer un fichier
    public Path loadFile(String fileName) {
        return this.fileStorageLocation.resolve(fileName).normalize();
    }

    // Méthode pour détecter le type de fichier (image, vidéo, etc.)
    private Integer detectFileType(MultipartFile file) {
        String contentType = file.getContentType();
        if (contentType != null) {
            if (contentType.startsWith("image")) {
                return 1; // Image
            } else if (contentType.startsWith("audio")) {
                return 2; // Audio
            } else if (contentType.startsWith("video")) {
                return 3; // Vidéo
            }
        }
        return 0; // Autre
    }
}
