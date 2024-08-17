package pfe.springboot.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import io.jsonwebtoken.io.IOException;
import pfe.springboot.entities.Commande;
import pfe.springboot.entities.Formateur;
import pfe.springboot.entities.Job;
import pfe.springboot.entities.cycle;
import pfe.springboot.services.offre.OffresServiceInter;

@RestController
@RequestMapping("/offres")
public class OffresController {

    @Autowired
    private OffresServiceInter service;


    @PostMapping("/ajouterOffre")
    public ResponseEntity<Job> createCommande(
            @RequestParam("nomOffre") String nomOffre,
            @RequestParam("description") String description,
                    @RequestParam("responsabilite") String responsabilite,
                            @RequestParam("qualifications") String qualifications

          ) throws java.io.IOException {

        try {
            Job job = new Job();
            job.setNomOffre(nomOffre);
            job.setDescription(description);
            job.setResponsabilite(responsabilite);
            job.setQualifications(qualifications);

            Job savedJob = service.saveJob(job);
            return ResponseEntity.ok(savedJob);

        } catch (IOException e) {
            throw new RuntimeException("Could not read the file!", e);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Object> addJob(
            @RequestParam("nomPrenom") String nomPrenom,
            @RequestParam("email") String email,
            @RequestParam("telephone") String telephone,
            @RequestParam("adresse") String adresse,
            @RequestParam("cv") MultipartFile cv) {
        try {
            service.saveJobApplication(nomPrenom, email, telephone, adresse, cv);

            // Return a JSON object instead of plain text
            Map<String, String> response = new HashMap<>();
            response.put("message", "Application submitted successfully.");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error submitting application.");
        }
    }

      @GetMapping(value = "/listeCandidats")
      public ResponseEntity<Map> Affichlist(Formateur ProfList) {
          return service.listCandidats();
      }
    
      @GetMapping(value = "/listeOffres")
      public ResponseEntity<Map> Affichlist(Job ProfList) {
          return service.listJobs();
      }

      @DeleteMapping(value = "/del/{id}")
      public void deleteOffre(@PathVariable Long id) {
          service.deleteOffre(id);
      }

      @PutMapping(value = "/modifierOffre/{id}")
      public Job updateprof(@PathVariable Long id, @RequestBody Job offremodifier) {
          return service.updateOffre(id, offremodifier);
      }

}
