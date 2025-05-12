package pixelpulse.eclesiasticasbackend.repository;

import pixelpulse.eclesiasticasbackend.model.Bautizo;
import pixelpulse.eclesiasticasbackend.model.Persona;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface BautizoRepository extends JpaRepository<Bautizo, Long> {
	List<Bautizo> findByIdBautizado(Persona persona);
	
	@Query("SELECT b FROM Bautizo b WHERE " +
            "b.idBautizado = :persona")
	List<Bautizo> findAllByIdPersonaInAnyRole (Persona persona);
}
