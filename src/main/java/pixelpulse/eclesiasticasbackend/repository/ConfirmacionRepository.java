package pixelpulse.eclesiasticasbackend.repository;

import pixelpulse.eclesiasticasbackend.model.Confirmacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConfirmacionRepository extends JpaRepository<Confirmacion, UUID> {
    
    @Query("SELECT c FROM Confirmacion c WHERE c.acta.id = :actaId")
    Optional<Confirmacion> findByActaId(@Param("actaId") UUID actaId);
}
