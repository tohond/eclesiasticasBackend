package pixelpulse.eclesiasticasbackend.repository;

import pixelpulse.eclesiasticasbackend.model.Persona;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
	Persona findPersonaById(Long id);
	boolean existsById(Long id);
	Persona deletePersonaById(Long id);
	List<Persona> findPersonaByNombre1AndNombre2AndApellido1AndApellido2(String nombre1,String nombre2,String apellido1,String apellido2);
	@Query("SELECT p FROM Persona p WHERE (p.nombre1 || ' ' || p.nombre2 ||' ' || p.apellido1 || ' ' || p.apellido2) ILIKE %:fullName%")
	List<Persona> findByFullNameContaining(@Param("fullName") String fullName);
}
