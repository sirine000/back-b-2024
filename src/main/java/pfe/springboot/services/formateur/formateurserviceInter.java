package pfe.springboot.services.formateur;

import org.springframework.web.multipart.MultipartFile;

import pfe.springboot.entities.Formateur;
import pfe.springboot.entities.cycle;

import java.util.List;
import java.util.Optional;

public interface formateurserviceInter {
    Formateur addformateur(Formateur f);

    String confirmdata(Formateur data);

    String confirmemail(Formateur email);

    public Formateur ajouterFormateur(Formateur formateur);

    Formateur updateProf(Long id_formateur, Formateur updateProf);

    // List<Formateur> listprof();

    Formateur userid(Long id_formateur);

    Formateur affichnp(String np);

    List<Formateur> listesearch(String email);

    List<Formateur> nomprof(String nomEtPrenom);

    public void savePhoto(Long id, MultipartFile file);

    List<String> getnoms();

    void deleteformateur(Long id_formateur);

    public Formateur connecter(String email, String password);

    Optional<cycle> getcycleid(Long idCycle);

    public Formateur activateFormateur(Long id);

    public Formateur deactivateFormateur(Long id);

}
