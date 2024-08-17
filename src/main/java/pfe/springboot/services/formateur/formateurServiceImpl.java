package pfe.springboot.services.formateur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import io.jsonwebtoken.io.IOException;
import pfe.springboot.entities.Formateur;
import pfe.springboot.entities.cycle;
import pfe.springboot.entities.role;
import pfe.springboot.entities.utilisateurs;
import pfe.springboot.repository.formateurRepository;
import pfe.springboot.repository.roleRepository;

import java.util.*;

@Service
public class formateurServiceImpl implements formateurserviceInter {
    @Autowired
    formateurRepository formateurRepository;

    @Override
    public Formateur ajouterFormateur(Formateur formateur) {
        Optional<Formateur> existingFormateur = formateurRepository.findByEmail(formateur.getEmail());
        if (existingFormateur.isPresent()) {
            throw new IllegalArgumentException("Un formateur avec cet e-mail existe déjà.");
        }
        return formateurRepository.save(formateur);
    }

    @Override
    public Formateur addformateur(Formateur f) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encryptedPassword = passwordEncoder.encode(f.getPassword());
        f.setPassword(encryptedPassword);

        return formateurRepository.save(f);
    }

    @Override
    public String confirmdata(Formateur data) {
        String ch = "";
        if (data.getPassword().equals(data.getConfPassword())) {
            formateurRepository.save(data);
            ch = "enregistrement fait avec succès !";

        } else {
            ch = "vérifier le mot de passe !";
        }

        return ch;

    }

    @Override
    public String confirmemail(Formateur email) {
        String ch1 = "";
        if (formateurRepository.existsByEmail(email.getEmail())) {

            ch1 = "email exist!";
        } else {
            formateurRepository.save(email);
            ch1 = "enregistrement fait avec succées!";
        }
        return ch1;
    }

    @Override
    public Formateur updateProf(Long id_formateur, Formateur formateurmodifier) {
        // Check if the formateur exists
        Formateur existingFormateur = formateurRepository.findById(id_formateur)
                .orElseThrow(() -> new RuntimeException("Formateur not found"));

        // Update the existing formateur with the new values
        existingFormateur.setNomEtPrenom(formateurmodifier.getNomEtPrenom());
        existingFormateur.setTel(formateurmodifier.getTel());
        existingFormateur.setEmail(formateurmodifier.getEmail());
        existingFormateur.setPassword(formateurmodifier.getPassword());
        // Add any other fields that need to be updated

        // Save the updated formateur
        return formateurRepository.save(existingFormateur);
    }

    // @Override
    public ResponseEntity<Map> listprof() {
        Map map = new HashMap();
        List<Formateur> formateurs = formateurRepository.findAll();
        map.put("formateurmap", formateurs);
        map.put("message ", "hello");
        map.put("message2", 12);

        return new ResponseEntity<>(map, HttpStatus.OK);
    }

    @Override
    public Formateur userid(Long id_formateur) {
        return formateurRepository.findById(id_formateur).orElse(null);
    }

    @Override
    public Formateur affichnp(String np) {
        return formateurRepository.findByNomEtPrenom(np);
    }

    @Override
    public void savePhoto(Long id, MultipartFile file) throws IOException {
        try {
            Formateur formateur = formateurRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Formateur not found"));
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            formateur.setPhoto(file.getBytes());
            formateurRepository.save(formateur);
        } catch (IOException e) {
            // Handle the exception as needed
            throw new RuntimeException("Failed to save photo", e);
        } catch (java.io.IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    @Override
    public List<Formateur> listesearch(String email) {
        return formateurRepository.userEmail(email);
    }

    @Override
    public List<Formateur> nomprof(String nomEtPrenom) {
        return formateurRepository.findAll();
    }

    @Override
    public List<String> getnoms() {
        List<String> nomsprof = new ArrayList<>();
        List<Formateur> formateurs = formateurRepository.findAll();
        for (Formateur formateur : formateurs) {
            nomsprof.add(formateur.getNomEtPrenom());
        }
        return nomsprof;
    }

    @Override
    public void deleteformateur(Long id_formateur) {
        formateurRepository.deleteById(id_formateur);

    }

    @Override
    public Formateur connecter(String email, String password) {
        return formateurRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public Optional<cycle> getcycleid(Long idCycle) {
        return Optional.empty();
    }

    @Override
    public Formateur activateFormateur(Long id) {
        Formateur formateur = formateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formateur not found"));
        formateur.setActive(true);
        return formateurRepository.save(formateur);
    }

    @Override
    public Formateur deactivateFormateur(Long id) {
        Formateur formateur = formateurRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Formateur not found"));
        formateur.setActive(false);
        return formateurRepository.save(formateur);
    }
}
