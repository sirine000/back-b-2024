package pfe.springboot.services.cycleservices;

import org.springframework.http.ResponseEntity;
import pfe.springboot.entities.cycle;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface cycleServInter {
    cycle ajoutercycle(cycle c);

    void delete(Long id_cycle);

    List<cycle> addListCycle(List<cycle> cy);

    public List<cycle> getCyclesForFormateur(Long formateurId);

    List<cycle> listecycle();

    cycle creercycle_formateur(Long id_formateur, cycle cycle);

    // cycle creercycle_formateur(String nomEtPrenom, cycle cycle);

    List<cycle> listecycleid();

    cycle modifiercycle(Long id_cycle, cycle cyclemodifier);

    Optional<cycle> getcycleid(Long id_cycle);

    cycle creercycle_formateurtessst(Long idFormateur, cycle cycle);

    // cycle getCycleById(Long idCycle);

    // ResponseEntity<Map> getcycleid(Long id_cycle);

    // List<cycle> listecycleidformateur(Long id_formateur);

}
