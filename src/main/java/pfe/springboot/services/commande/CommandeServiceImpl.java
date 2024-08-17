package pfe.springboot.services.commande;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import pfe.springboot.entities.Commande;
import pfe.springboot.entities.Formateur;
import pfe.springboot.entities.cours;
import pfe.springboot.entities.cycle;
import pfe.springboot.repository.CommandeRepository;
import pfe.springboot.repository.coursRepo;

@Service
public class CommandeServiceImpl implements CommandeServiceInter {

    @Autowired
    private coursRepo coursRepository;
    @Autowired

    private CommandeRepository commandeRepository;

    public Commande saveCommande(Commande commande) {
        return commandeRepository.save(commande);
    }

    @Override
    public ResponseEntity<Map> listDemandes() {
        Map map = new HashMap();
        List<Commande> commandes = commandeRepository.findAll();
        map.put("commandes", commandes);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @Override
    public List<cycle> getValidCoursesForUser(String email) {
        // Find commands by email and verifier set to true
        List<Commande> commandes = commandeRepository.findByEmailAndVerifier(email, true);

        // Map to store cycleId as key and list of courses as value
        List<cycle> coursesByCycle = new ArrayList<>();

        for (Commande commande : commandes) {
            if (commande.getCycle() != null) {
                coursesByCycle.add(commande.getCycle());
            }
        }


        // // Fetch courses for each cycle ID and populate the map
        // for (Long cycleId : cycleIds) {
        //     List<cours> courses = coursRepository.findByCycleId(cycleId);
        //     if (!courses.isEmpty()) {
        //         coursesByCycle.put(cycleId, courses);
        //     }
        // }

        return coursesByCycle;
    }
}