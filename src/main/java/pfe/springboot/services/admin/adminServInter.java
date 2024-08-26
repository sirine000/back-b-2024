package pfe.springboot.services.admin;

import pfe.springboot.entities.admin;

public interface adminServInter {
     public admin connecter(String email, String password);
     admin updateAdmin(Long id_admin, admin updateProf);

}
