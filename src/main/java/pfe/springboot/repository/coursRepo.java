package pfe.springboot.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pfe.springboot.entities.cours;
import pfe.springboot.entities.cycle;

@Repository
public interface coursRepo extends JpaRepository<cours, Long> {
    List<cours> findByCycleId(Long cycleId);
    
    List<cours> findByCycleIn(List<cycle> cycles);
    

}
