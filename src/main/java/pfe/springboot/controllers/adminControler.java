package pfe.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pfe.springboot.entities.admin;
import pfe.springboot.services.admin.adminServInter;

@RestController
@RequestMapping("/admin")
public class adminControler {

    @Autowired
    private adminServInter adminServiceInter;

    @GetMapping("/login")
    public ResponseEntity<?> seConnecterAdmin(@RequestParam String email, @RequestParam String password) {
        admin admin = adminServiceInter.connecter(email, password);
        if (admin == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Identifiants invalides");
        }
        System.out.println(email);
        return ResponseEntity.ok(admin);
    }

    @PutMapping(value = "/modifieradmin/{id_admin}")
    public admin updateprof(@PathVariable Long id_admin, @RequestBody admin adminmodifier) {
        return adminServiceInter.updateAdmin(id_admin, adminmodifier);
    }

}

class AuthRequest {
    private String username;
    private String password;

    // getters and setters
}

class AuthResponse {
    private String jwt;

    public AuthResponse(String jwt) {
        this.jwt = jwt;
    }

    // getter
}
