package pfe.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pfe.springboot.entities.Formateur;
import pfe.springboot.entities.cycle;

import java.util.List;
import java.util.Optional;

@Repository
public interface cycleRepo extends JpaRepository<cycle, Long> {

//    @Query("SELECT c FROM cycle c JOIN FETCH c.formateur WHERE c.id_cycle = :id_cycle")
//    cycle findCycleWithFormateur(@Param("id_cycle") Long idCycle);

    @Query("SELECT  f.nomEtPrenom FROM cycle c JOIN c.formateur f")
    List<Object[]> getCyclefromateurtest();

    @Query("SELECT c FROM cycle c WHERE c.formateur.id_formateur = :id_formateur")
    List<cycle> findCyclesByFormateurId(@Param("id_formateur") Long id_formateur);

    // List<cycle> findById(Long idCycle);

//    Optional<Formateur> findByNom(String nomEtPrenom);
}
