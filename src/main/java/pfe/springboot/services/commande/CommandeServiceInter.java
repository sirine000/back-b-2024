package pfe.springboot.services.commande;

import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;

import pfe.springboot.entities.Commande;
import pfe.springboot.entities.cycle;

public interface CommandeServiceInter {

    public Commande saveCommande(Commande commande);

    public ResponseEntity<Map> listDemandes();

    List<cycle> getValidCoursesForUser(String email);
}
