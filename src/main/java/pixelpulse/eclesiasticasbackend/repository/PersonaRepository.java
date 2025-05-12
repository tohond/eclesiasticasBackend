package pixelpulse.eclesiasticasbackend.repository;

import pixelpulse.eclesiasticasbackend.model.Persona;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, UUID> {
	Persona findPersonaById(UUID id);
	boolean existsById(UUID id);
	Persona deletePersonaById(UUID id);
	Persona findPersonaByNombre(String nombre);
	List<Persona> findPersonaByNombreContains(String name);
}
