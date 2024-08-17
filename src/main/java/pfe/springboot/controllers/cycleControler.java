package pfe.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pfe.springboot.entities.Formateur;
import pfe.springboot.entities.cycle;
import pfe.springboot.repository.cycleRepo;
import pfe.springboot.repository.formateurRepository;
import pfe.springboot.services.cycleservices.cycleServImpl;
import pfe.springboot.services.cycleservices.cycleServInter;
import pfe.springboot.services.formateur.formateurServiceImpl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/cycle")
public class cycleControler {
    @Autowired
    cycleServInter cycleServInter;
@Autowired
cycleServImpl cycleServiceimpl;

@Autowired
private formateurRepository formateurRepository;
  
@Autowired
private cycleRepo cycleRepository;

@PostMapping("/ajoutercycle")
public ResponseEntity<?> ajouterCycle(@RequestBody Map<String, Object> cycleData) {
    // Log received data
    System.out.println("Received data: " + cycleData);

    if (!cycleData.containsKey("idFormateur") || cycleData.get("idFormateur") == null) {
        return ResponseEntity.badRequest().body("idFormateur is missing or null");
    }

    Long idFormateur;
    try {
        idFormateur = Long.parseLong(cycleData.get("idFormateur").toString()); // Convert to Long from String
    } catch (NumberFormatException e) {
        return ResponseEntity.badRequest().body("Invalid idFormateur format");
    }

    Optional<Formateur> formateurOpt = formateurRepository.findById(idFormateur);

    if (formateurOpt.isPresent()) {
        cycle cycle = new cycle();
        cycle.setNom_cycle_de_formation((String) cycleData.get("nomCycleDeFormation"));
        cycle.setDescription((String) cycleData.get("description"));
        cycle.setPrix(Long.parseLong(cycleData.get("prix").toString()));

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        try {
            cycle.setDateDebut(dateFormat.parse((String) cycleData.get("dateDebut")));
            cycle.setDateFin(dateFormat.parse((String) cycleData.get("dateFin")));
        } catch (ParseException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid date format");
        }

        cycle.setFormateur(formateurOpt.get());
        cycle savedCycle = cycleRepository.save(cycle);
        return ResponseEntity.ok(savedCycle);
    } else {
        return ResponseEntity.badRequest().body("Formateur not found with id: " + idFormateur);
    }
}


    @DeleteMapping(value = "/deletecycle/{id_cycle}")
    public void delete(@PathVariable Long id_cycle) {
        cycleServInter.delete(id_cycle);
    }

@PostMapping(value = "/addlist")
 public List<cycle> addListCycle(@RequestBody List<cycle> CY){
        return cycleServInter.addListCycle(CY);
}


    @GetMapping(value = "listecycle")
    public List<cycle> listecycle(){
        return cycleServInter.listecycle();    }

    @Autowired
    formateurServiceImpl formateurServiceInter;

    @GetMapping(value = "/listenomprof")
    public List<String> Affichliste() {

        return formateurServiceInter.getnoms();
    }
/*@DeleteMapping(value = "suppCycle")
    public void cycle(){
        this.cycleServInter.supprimercycle();
    }*/

    @PostMapping("/ajouterbyid/{id_formateur}")
    public cycle creercycle_formateur(@PathVariable Long id_formateur, @RequestBody cycle cycle ){
        return cycleServInter.creercycle_formateurtessst(id_formateur,cycle);
    }

    @GetMapping("/getlistecycleid")
    public List<cycle> listecycleid(){
        return cycleServInter.listecycleid();
    }

//    @GetMapping("/getlistecycleid/{id_formateur}")
//    public List<cycle> listecycleidformateur(@PathVariable Long id_formateur){
//        return cycleServInter.listecycleidformateur(id_formateur);
//    }

@PutMapping("/modifiercycle/{id_cycle}")
public ResponseEntity<cycle> modifiercycle(@PathVariable Long id_cycle, @RequestBody cycle cyclemodifier) {
  cycle updatedCycle = cycleServInter.modifiercycle(id_cycle, cyclemodifier);
  if (updatedCycle != null) {
    return ResponseEntity.ok(updatedCycle);
  } else {
    return ResponseEntity.notFound().build();
  }
}

    @GetMapping(value="/getcycleid/{id_cycle}")
    public Optional<cycle> getcycleid(@PathVariable Long id_cycle) {
        return cycleServInter.getcycleid(id_cycle);
    }

    @GetMapping("/formateurscycles/{formateurId}")
    public List<cycle> getCyclesForFormateur(@PathVariable Long formateurId) {
        return cycleServInter.getCyclesForFormateur(formateurId);
    }

//    @GetMapping("/cycles/{idCycle}")
//    public ResponseEntity<cycle> getCycleById(@PathVariable Long idCycle) {
//        cycle cycle = cycleServiceimpl.getCycleById(idCycle);
//        if (cycle != null) {
//            return ResponseEntity.ok(cycle);
//        } else {
//            return ResponseEntity.notFound().build();
//        }
//    }




//
//    @GetMapping("/formateurs")
//    public List<Object[]> getCycleAndFormateurNames() {
//        return cycleServiceimpl.getCycleAndFormateurNames();
//    }


//    @GetMapping("/formateurstest")
//    public List<Object[]> getCycleAndFormateurNames() {
//        return cycleServiceimpl.getCycleformateur();
//    }
//
//    @PostMapping("/creer/{id_formateur}")
//    public cycle creerCycleFormateur(@PathVariable Long id_formateur, @RequestBody cycle cycle) {
//        return cycleServiceimpl.creercycle_formateur(id_formateur, cycle);
//    }

}
