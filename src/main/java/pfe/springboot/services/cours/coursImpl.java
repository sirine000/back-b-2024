package pfe.springboot.services.cours;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pfe.springboot.entities.cours;
import pfe.springboot.repository.coursRepo;

@Service
public class coursImpl implements coursInter {
    @Autowired
    coursRepo coursRepo;

    @Override
    public cours addcours(cours Vcours) {
        return coursRepo.save(Vcours);
    }

    @Autowired
    private coursRepo coursRepository;

    @Override
    public cours saveCours(cours cours) {
        return coursRepository.save(cours);
    }


    // @Override
    // public cours updateCours(Long id, cours cours) {
    // Optional<cours> existingCours = coursRepository.findById(id);
    // if (existingCours.isPresent()) {
    // cours updatedCours = existingCours.get();
    // updatedCours.setNomCours(cours.getNomCours());
    // updatedCours.setCoursFile(cours.getCoursFile());
    // updatedCours.setDescription(cours.getDescription());
    // updatedCours.setCycle(cours.getCycle());
    // return coursRepository.save(updatedCours);
    // }
    // return null;
    // }

    @Override
    public void deleteCours(Long id) {
        coursRepository.deleteById(id);
    }

    @Override
    public cours getCoursById(Long id) {
        return coursRepository.findById(id).orElse(null);
    }

    @Override
    public List<cours> getAllCours() {
        return coursRepository.findAll();
    }

    @Override
    public List<cours> getCoursByCycleId(Long cycleId) {
        return coursRepository.findByCycleId(cycleId);
    }
}
