package pfe.springboot.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pfe.springboot.entities.Offre;

@Repository
public interface OffresRepository extends JpaRepository<Offre, Long> {
}
