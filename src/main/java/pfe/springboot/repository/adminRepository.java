package pfe.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import pfe.springboot.entities.admin;

import java.util.List;
import java.util.Optional;

@Repository
public interface adminRepository extends JpaRepository<admin, Long> {

    boolean existsByEmail(String email);

    Optional<admin> findByEmail(String email);

    admin findByNomEtPrenom(String nomEtPrenom);

    admin findByEmailAndPassword(String email, String password);

    @Query(value = "select * from admin f  where f.email like :cle%", nativeQuery = true)
    List<admin> userEmail(@Param("cle") String email);

    // Optional<Formateur> findByn(String nomEtPrenom);
}