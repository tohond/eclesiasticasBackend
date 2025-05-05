package pixelpulse.eclesiasticasbackend.repository;

import pixelpulse.eclesiasticasbackend.model.Matrimonio;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface MatrimonioRepository extends JpaRepository<Matrimonio, UUID> {
    
    @Query("SELECT m FROM Matrimonio m WHERE m.acta.id = :actaId")
    Optional<Matrimonio> findByActaId(@Param("actaId") UUID actaId);
}
