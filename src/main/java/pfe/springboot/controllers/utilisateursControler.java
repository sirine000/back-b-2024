package pfe.springboot.controllers;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pfe.springboot.entities.Formateur;
import pfe.springboot.entities.utilisateurs;
import pfe.springboot.services.utilisateurs.utilisateursServiceInter;

@RestController
@RequestMapping("/utilisateurs")
@CrossOrigin(origins = "http://localhost:4200")

public class utilisateursControler {

    @Autowired
    utilisateursServiceInter utilisateursServiceInter;

    @PostMapping(value = "/ajouterutilisateur")
    public utilisateurs addutilisateurs(@RequestBody utilisateurs u) {
        return utilisateursServiceInter.addutilisateurs(u);

    }
}
