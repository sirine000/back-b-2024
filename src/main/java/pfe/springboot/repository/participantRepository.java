package pfe.springboot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pfe.springboot.entities.participant;
import java.util.List;
import java.util.Optional;

@Repository
public interface participantRepository extends JpaRepository<participant, Long> {

    boolean existsByEmail(String email);

    participant findByEmail(String email);

    participant findByNom(String nom);

    participant findByResetToken(String resetToken);

    participant findByEmailAndPassword(String email, String password);

    @Query(value = "select * from participant fé  where f.email like :cle%", nativeQuery = true)
    List<participant> userEmail(@Param("cle") String email);
}