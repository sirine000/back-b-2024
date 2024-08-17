package pfe.springboot.controllers;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import io.jsonwebtoken.io.IOException;
import pfe.springboot.entities.cours;
import pfe.springboot.entities.cycle;
import pfe.springboot.repository.coursRepo;
import pfe.springboot.repository.cycleRepo;
import pfe.springboot.services.cours.coursInter;

@RestController
@RequestMapping(value = "/cours")
@CrossOrigin(origins = "http://localhost:4200")

public class coursControl {
    @Autowired
    coursInter coursInter;
    
    @Autowired
    private coursRepo coursRepository;

    @Autowired
    private cycleRepo cycleRepository;

    @PostMapping("/inserercyclecours")
    public ResponseEntity<String> insererCycleCours(
            @RequestParam("fichiers") List<MultipartFile> fichiers,
            @RequestParam("nomCours") String nomCours,
            @RequestParam("description") String description,
            @RequestParam("idCycle") Long idCycle) throws java.io.IOException {

        try {
            List<byte[]> filePaths = new ArrayList<>();
            String uploadDir = "C:/Users/Montasser Brahem/OneDrive/Bureau/pfe-sirine/springboot/uploads";
         
            File dir = new File(uploadDir);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            // Process each file
            for (MultipartFile fichier : fichiers) {
                try {
                    byte[] fileContent = fichier.getBytes(); // Convert file to byte array
                    filePaths.add(fileContent);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            // Find the cycle by ID
            cycle cycle = cycleRepository.findById(idCycle)
                    .orElseThrow(() -> new IllegalArgumentException("Invalid cycle ID"));

            // Create and save the course
            cours cours = new cours();
            cours.setNomCours(nomCours);
            cours.setDescription(description);
            cours.setCycle(cycle);
            cours.setFileContents(filePaths); // Save the file paths

            coursRepository.save(cours);

            return ResponseEntity.ok("Fichiers uploadés avec succès");
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de l'upload des fichiers: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Erreur: " + e.getMessage());
        }
    }

    // @PutMapping("/update/{id}")
    // public ResponseEntity<cours> updateCours(@PathVariable Long id, @RequestBody cours cours) {
    //     return ResponseEntity.ok(coursInter.updateCours(id, cours));
    // }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteCours(@PathVariable Long id) {
        coursInter.deleteCours(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<cours>> getAllCours() {
        return ResponseEntity.ok(coursInter.getAllCours());
    }

    @GetMapping("/{id}")
    public ResponseEntity<cours> getCoursById(@PathVariable Long id) {
        return ResponseEntity.ok(coursInter.getCoursById(id));
    }

    @GetMapping("/cycle/{cycleId}")
    public ResponseEntity<List<cours>> getCoursByCycleId(@PathVariable Long cycleId) {
        return ResponseEntity.ok(coursInter.getCoursByCycleId(cycleId));
    }
}
