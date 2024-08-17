package pfe.springboot.services.cours;

import java.util.List;

import pfe.springboot.entities.cours;

public interface coursInter {
    cours addcours(cours Vcours);
    
    cours saveCours(cours cours);

    // cours updateCours(Long id, cours cours);

    void deleteCours(Long id);

    cours getCoursById(Long id);

    List<cours> getAllCours();

    List<cours> getCoursByCycleId(Long cycleId);
}
