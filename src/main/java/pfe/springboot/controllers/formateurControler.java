package pfe.springboot.controllers;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import pfe.springboot.entities.Formateur;
import pfe.springboot.entities.cours;
import pfe.springboot.entities.cycle;
import pfe.springboot.repository.coursRepo;
import pfe.springboot.repository.cycleRepo;
import pfe.springboot.services.formateur.formateurServiceImpl;
import pfe.springboot.services.formateur.formateurserviceInter;

import java.util.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
// @CrossOrigin(origins="*",allowedHeaders = "*")
@RequestMapping("/formateur")
public class formateurControler {
    @Autowired
    formateurserviceInter formateurServiceInter;
    @Autowired
    private coursRepo coursRepository;

    @Autowired
    private cycleRepo cycleRepository;

    @PostMapping("/ajouterformateur")
    public ResponseEntity<?> ajouterFormateur(@Valid @RequestBody Formateur formateur, BindingResult result) {
        if (result.hasErrors()) {
            Map<String, String> errors = new HashMap<>();
            result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }

        try {
            Formateur nouveauFormateur = formateurServiceInter.ajouterFormateur(formateur);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Formateur ajouté avec succès");
            return new ResponseEntity<>(response, HttpStatus.CREATED);
        } catch (IllegalArgumentException e) {
            Map<String, String> errorResponse = new HashMap<>();
            errorResponse.put("message", e.getMessage());
            return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/seconnecterformateur")
    public ResponseEntity<?> seConnecterFormateur(@RequestParam String email, @RequestParam String password) {
        Formateur formateur = formateurServiceInter.connecter(email, password);
        if (formateur == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants invalides");
        }
        if (!formateur.isActive()) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Le compte n'est pas actif");
        }
        return ResponseEntity.ok(formateur);
    }

    @PostMapping(value = "/confirmdata")
    public String confirmdata(@RequestBody Formateur data) {
        return formateurServiceInter.confirmdata(data);

    }

    @PostMapping(value = "/confirmemail")
    public String emailConf(@RequestBody Formateur email) {
        return formateurServiceInter.confirmemail(email);
    }

    @PutMapping(value = "/updateEmail/{id_formateur}")
    public Formateur updateFormateur(@PathVariable Long id_formateur, @RequestBody Formateur updateProf) {
        return formateurServiceInter.updateProf(id_formateur, updateProf);
    }

    @PostMapping("/uploadPhoto/{id}")
    public ResponseEntity<Map<String, String>> uploadPhoto(@PathVariable Long id,
            @RequestParam("file") MultipartFile file) {
        try {
            formateurServiceInter.savePhoto(id, file);
            Map<String, String> response = new HashMap<>();
            response.put("message", "Photo uploaded successfully");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> response = new HashMap<>();
            response.put("message", "Échec du téléchargement de la photo");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    @Autowired
    formateurServiceImpl formateurservice;

    @GetMapping(value = "/listeFormateur")
    public ResponseEntity<Map> Affichlist(Formateur ProfList) {
        return formateurservice.listprof();
    }

    @GetMapping(value = "/userid/{id_formateur}")
    public Formateur userid(@PathVariable Long id_formateur) {
        return formateurServiceInter.userid(id_formateur);

    }

    @GetMapping(value = "/nomPrenom/{np}")
    public Formateur AffichNP(@PathVariable String np) {
        return formateurServiceInter.affichnp(np);
    }

    @GetMapping(value = "/search/{email}")
    public List<Formateur> listesearch(@PathVariable String email) {
        return formateurServiceInter.listesearch(email);
    }

    /*
     * @GetMapping(value = "/listenomprof")
     * public List<Formateur> Affichliste(String nomEtPrenom) {
     * return formateurServiceInter.listprof();
     * }
     */
    @GetMapping(value = "/listenomprof")
    public List<String> Affichliste() {
        return formateurServiceInter.getnoms();
    }

    @DeleteMapping(value = "/del/{id_formateur}")
    public void deleteformateur(@PathVariable Long id_formateur) {
        formateurServiceInter.deleteformateur(id_formateur);
    }

    @PutMapping(value = "/modifierformateur/{id_formateur}")
    public Formateur updateprof(@PathVariable Long id_formateur, @RequestBody Formateur formateurmodifier) {
        return formateurServiceInter.updateProf(id_formateur, formateurmodifier);
    }

    @PutMapping("/activateFormateur/{id}")
    public ResponseEntity<Formateur> activateFormateur(@PathVariable Long id) {
        Formateur updatedFormateur = formateurServiceInter.activateFormateur(id);
        return ResponseEntity.ok(updatedFormateur);
    }

    @PutMapping("/deactivateFormateur/{id}")
    public ResponseEntity<Formateur> deactivateFormateur(@PathVariable Long id) {
        Formateur updatedFormateur = formateurServiceInter.deactivateFormateur(id);
        return ResponseEntity.ok(updatedFormateur);
    }

    @GetMapping(value = "/getcycleid/{id_cycle}")
    public Optional<cycle> getformateurteurbid(@PathVariable Long id_cycle) {
        return formateurServiceInter.getcycleid(id_cycle);
    }

        @GetMapping("/coursbyFormateur/{formateurId}")
    public ResponseEntity<List<cours>> getCoursByFormateur(@PathVariable Long formateurId) {
        try {
            // Récupérer les cycles associés au formateur
            List<cycle> cycles = cycleRepository.findCyclesByFormateurId(formateurId);
            if (cycles.isEmpty()) {
                return ResponseEntity.noContent().build();
            }

            // Récupérer les cours associés aux cycles
            List<cours> coursList = coursRepository.findByCycleIn(cycles);
            return ResponseEntity.ok(coursList);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


}
