package pfe.springboot.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import io.jsonwebtoken.io.IOException;
import pfe.springboot.entities.Commande;
import pfe.springboot.entities.Formateur;
import pfe.springboot.entities.cours;
import pfe.springboot.entities.cycle;
import pfe.springboot.repository.CommandeRepository;
import pfe.springboot.repository.cycleRepo;
import pfe.springboot.services.commande.CommandeServiceInter;
import pfe.springboot.services.cours.coursImpl;

@RestController
@RequestMapping("/commandes")
public class CommandeController {

    @Autowired
    private CommandeServiceInter commandeService;

    @Autowired 
    private CommandeRepository commandeRepository;
    
    @Autowired
    private cycleRepo cycleRepository;

    @Autowired
    private coursImpl coursService;

    @PostMapping("/add")
    public ResponseEntity<Commande> createCommande(
            @RequestParam("nomPrenom") String nomPrenom,
            @RequestParam("email") String email,
            @RequestParam("telephone") String telephone,
            @RequestParam("adresse") String adresse,
            @RequestParam("cycleId") Long cycleId,
            @RequestParam("bonDeCommande") MultipartFile bonDeCommande) throws java.io.IOException {

        try {
            Commande commande = new Commande();
            commande.setNomPrenom(nomPrenom);
            commande.setEmail(email);
            commande.setTelephone(telephone);
            commande.setAdresse(adresse);

            // Fetch the cycle by ID and set it in the Commande
            cycle cycle = cycleRepository.findById(cycleId)
                    .orElseThrow(() -> new RuntimeException("Cycle not found with id " + cycleId));
            commande.setCycle(cycle);

            // Convert MultipartFile to byte array
            commande.setBonDeCommande(bonDeCommande.getBytes());
            // System.out.println(commande)
            // Save the Commande
            Commande savedCommande = commandeService.saveCommande(commande);
            return ResponseEntity.ok(savedCommande);

        } catch (IOException e) {
            throw new RuntimeException("Could not read the file!", e);
        }
    }

@PutMapping("/{id}/verifier")
public ResponseEntity<Map<String, String>> verifierCommande(@PathVariable Long id) {
    Optional<Commande> commandeOpt = commandeRepository.findById(id);
    if (commandeOpt.isPresent()) {
        Commande commande = commandeOpt.get();
        commande.setVerifier(true);
        commandeRepository.save(commande);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Commande vérifiée avec succès.");
        return ResponseEntity.ok(response);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.singletonMap("error", "Commande non trouvée."));
    }
}

    @GetMapping(value = "/listeCommandes")
    public ResponseEntity<Map> Affichlist(Formateur ProfList) {
        return commandeService.listDemandes();
    }

       @GetMapping("/valid-courses")
    public List<cycle> getValidCoursesForUser(@RequestParam String email) {
        return commandeService.getValidCoursesForUser(email);
    }

    @GetMapping("/courses")
    public ResponseEntity<List<cours>> getCoursesByCycleId(@RequestParam Long cycleId) {
        if (cycleId == null) {
            return ResponseEntity.badRequest().build();
        }
        List<cours> courses = coursService.getCoursByCycleId(cycleId);
        if (courses.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        System.out.println(courses);
        return ResponseEntity.ok(courses);
    }
}
