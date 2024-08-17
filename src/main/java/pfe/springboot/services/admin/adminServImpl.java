package pfe.springboot.services.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pfe.springboot.entities.Admin;
import pfe.springboot.entities.Formateur;
import pfe.springboot.repository.adminRepository;

@Service
public class adminServImpl implements adminServInter {
    @Autowired
    adminRepository adminRepository;

    @Override
    public Admin connecter(String email, String password) {
        return adminRepository.findByEmailAndPassword(email, password);
    }

    @Override
    public Admin updateAdmin(Long id_admin, Admin adminmodifier) {
        // Check if the admin exists
        Admin existingAdmin = adminRepository.findById(id_admin)
                .orElseThrow(() -> new RuntimeException("Admin not found"));

        // Update the existing admin with the new values
        existingAdmin.setNomEtPrenom(adminmodifier.getNomEtPrenom());
        existingAdmin.setTel(adminmodifier.getTel());
        existingAdmin.setEmail(adminmodifier.getEmail());
        existingAdmin.setPassword(adminmodifier.getPassword());
        // Add any other fields that need to be updated

        // Save the updated admin
        return adminRepository.save(existingAdmin);
    }

}
