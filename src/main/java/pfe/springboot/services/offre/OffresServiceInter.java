package pfe.springboot.services.offre;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

import pfe.springboot.entities.Job;
import pfe.springboot.entities.Offre;

public interface OffresServiceInter {
    public Offre saveOffre(Offre offre) throws IOException;
    public void saveJobApplication(String nomPrenom, String email, String telephone, String adresse,
            MultipartFile cv) throws IOException;

    public ResponseEntity<Map> listCandidats();

    void deleteOffre(Long id);
   
    public ResponseEntity<Map> listJobs();

   Job updateOffre(Long id, Job updateoffre);

    public Job saveJob(Job job) throws IOException;
}
