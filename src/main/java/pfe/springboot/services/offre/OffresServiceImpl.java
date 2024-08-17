package pfe.springboot.services.offre;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// JobApplicationService.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import pfe.springboot.entities.Commande;
import pfe.springboot.entities.Formateur;
import pfe.springboot.entities.Job;
import pfe.springboot.entities.Offre;
import pfe.springboot.repository.OffresRepository;
import pfe.springboot.repository.jobRepository;

@Service
public class OffresServiceImpl implements OffresServiceInter {

    @Autowired
    private OffresRepository repository;

    @Autowired
    private jobRepository jobRepository;

    @Override
    public Offre saveOffre(Offre offre) {
        return repository.save(offre);
    }

    @Override
    public void saveJobApplication(String nomPrenom, String email, String telephone, String adresse, MultipartFile cv)
            throws IOException {
        Offre application = new Offre();
        application.setNomPrenom(nomPrenom);
        application.setEmail(email);
        application.setTelephone(telephone);
        application.setAdresse(adresse);
        if (cv != null && !cv.isEmpty()) {
            application.setCv(cv.getBytes());
        }
        repository.save(application);
    }

     @Override
     public ResponseEntity<Map> listCandidats() {
         Map map = new HashMap();
         List<Offre> candidats = repository.findAll();
         map.put("candidats", candidats);

         return new ResponseEntity<>(map, HttpStatus.OK);
     }
    
     @Override
     public Job saveJob(Job job) {
         return jobRepository.save(job);
     }

     
    @Override
    public Job updateOffre(Long id, Job offremodifier) {
        // Check if the formateur exists
        Job existingJob = jobRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        // Update the existing formateur with the new values
        existingJob.setNomOffre(offremodifier.getNomOffre());
        existingJob.setDescription(offremodifier.getDescription());
        existingJob.setResponsabilite(offremodifier.getResponsabilite());
        existingJob.setQualifications(offremodifier.getQualifications());

        // Save the updated Offre
        return jobRepository.save(existingJob);
    }


        @Override
        public ResponseEntity<Map> listJobs() {
            Map map = new HashMap();
            List<Job> jobs = jobRepository.findAll();
            map.put("jobs", jobs);

            return new ResponseEntity<>(map, HttpStatus.OK);
        }
    
    @Override
    public void deleteOffre(Long id) {
        jobRepository.deleteById(id);

    }
}
