package pixelpulse.eclesiasticasbackend.repository;

import pixelpulse.eclesiasticasbackend.model.Matrimonio;
import pixelpulse.eclesiasticasbackend.model.Persona;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MatrimonioRepository extends JpaRepository<Matrimonio, Long> {
    
    @Query("SELECT m FROM Matrimonio m WHERE m.acta.id = :actaId")
    Optional<Matrimonio> findByActaId(@Param("actaId") Long actaId);

	Matrimonio findByPersonaA(Persona p);
	
	
	@Query("SELECT m FROM Matrimonio m WHERE " +
	           "m.personaA = :persona OR " +
	           "m.personaB = :persona OR " +
	           "m.idMadrina = :persona OR " +
	           "m.idPadrino = :persona")
	    List<Matrimonio> findAllByPersonaInAnyRole(@Param("persona") Persona persona);
}
