package pfe.springboot.services.cycleservices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pfe.springboot.entities.Formateur;
import pfe.springboot.entities.cycle;
import pfe.springboot.repository.cycleRepo;
import pfe.springboot.repository.formateurRepository;

import java.util.List;
import java.util.Optional;

@Service
public class cycleServImpl implements cycleServInter{


    @Autowired
    cycleRepo cycleRepo;
    @Autowired
    formateurRepository formateurRepository;

    @Override
    public cycle ajoutercycle(cycle c ){

        return  cycleRepo.save(c);
    }


    @Override
    public void delete(Long id_cycle){
        cycleRepo.deleteById(id_cycle );
    }

    @Override
    public List<cycle> addListCycle(List<cycle> cy) {
        return cycleRepo.saveAll(cy);
    }

    @Override
    public List<cycle> getCyclesForFormateur(Long formateurId) {
        return cycleRepo.findCyclesByFormateurId(formateurId);
    }
    
    @Override
    public List<cycle> listecycle() {
        return cycleRepo.findAll();
    }



    @Override
    public cycle creercycle_formateur(Long id_formateur, cycle cycle) {

        Optional<Formateur> formateurs=formateurRepository.findById(id_formateur);
                if(formateurs.isPresent())
                {

                    Formateur formateur = new Formateur();

                    cycle.setFormateur(formateurs.get());
                    formateur.setNomEtPrenom(formateur.getNomEtPrenom());

                    formateurRepository.save(formateur);

                 return  cycleRepo.save(cycle);

                }else{
                    throw new IllegalArgumentException("formateur not found !");
                }

    }


//    @Override
//    public cycle creercycle_formateur(Long id_formateur, cycle cycle) {
//        Optional<Formateur> formateurOptional = formateurRepository.findById(id_formateur);
//
//        if (formateurOptional.isPresent()) {
//            Formateur formateur = formateurOptional.get();
//            cycle.setFormateur(formateur);
//            formateur.setNomEtPrenom(formateur.getNomEtPrenom());
//
//            Cycle cycle = cycleRepository.findById(id); // Supposons que vous avez une méthode findById dans votre cycleRepository
//            if (cycle != null && cycle.getFormateur() != null) {
//                String nomFormateur = cycle.getFormateur().getNom(); // Accès au nom du formateur
//                cycle.setNomFormateur(nomFormateur); // Définition du nom du formateur dans l'entité Cycle
//            }
//            return cycleRepo.save(cycle);
//        } else {
//            throw new IllegalArgumentException("Formateur not found with nom: " + id_formateur);
//        }
//    }

//    @Override
//    public cycle creercycle_formateur(Long id_formateur, cycle cycle) {
//        Optional<Formateur> formateurOptional = formateurRepository.findById(id_formateur);
//
//        if (formateurOptional.isPresent()) {
//            Formateur formateur = formateurOptional.get();
//            cycle.setFormateur(formateur);
//            cycle = cycleRepo.save(cycle); // Sauvegarde du cycle avec le formateur associé
//            return cycle;
//        } else {
//            throw new IllegalArgumentException("Formateur not found with id: " + id_formateur);
//        }
//    }

    @Override
    public List<cycle> listecycleid() {
        return cycleRepo.findAll();
    }

    @Override
    public cycle modifiercycle(Long id_cycle, cycle cyclemodifier) {
        cycle cycleModifier= cycleRepo.findById(id_cycle).get();
      cycleModifier.setNom_cycle_de_formation(cyclemodifier.getNom_cycle_de_formation());
        cycleModifier.setDescription(cyclemodifier.getDescription());
        cycleModifier.setPrix(cyclemodifier.getPrix());
        cycleModifier.setDateDebut(cyclemodifier.getDateDebut());
        cycleModifier.setDateFin(cyclemodifier.getDateFin());
        cycleModifier.setFormateur(cyclemodifier.getFormateur());

        return cycleRepo.save(cycleModifier);
    }
//cycle id
    @Override
    public Optional<cycle> getcycleid(Long id_cycle) {
        return cycleRepo.findById(id_cycle);
    }

//    @Override
//    public cycle getCycleById(Long idCycle) {
//        return null;
//    }

//    @Override
//    public cycle getCycleWithFormateur(Long id_cycle) {
//        return cycleRepo.findCycleWithFormateur(id_cycle);
//    }

//    public List<Object[]> getCycleAndFormateurNames() {
//        return cycleRepo.getCycleAndFormateurNames();
//    }


    public List<Object[]> getCycleformateur() {
        return cycleRepo.getCyclefromateurtest();
    }
@Override
    public cycle creercycle_formateurtessst(Long id_formateur, cycle cycle) {
        Optional<Formateur> formateurOptional = formateurRepository.findById(id_formateur);
        if (formateurOptional.isPresent()) {
            Formateur formateur = formateurOptional.get();
            cycle.setFormateur(formateur);
            return cycleRepo.save(cycle);
        } else {            throw new IllegalArgumentException("Formateur not found!");
        }
    }


}
