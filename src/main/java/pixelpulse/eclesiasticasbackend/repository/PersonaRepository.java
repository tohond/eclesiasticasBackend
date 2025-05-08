package pixelpulse.eclesiasticasbackend.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
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

	/**
	 * Busca personas cuyo nombre contenga la cadena proporcionada
	 * @param nombre Cadena a buscar en el nombre
	 * @return Lista de personas que coinciden con el criterio
	 */
	@Query("SELECT p FROM Persona p WHERE LOWER(p.nombre) LIKE LOWER(CONCAT('%', :nombre, '%'))")
	List<Persona> findByNombreContaining(@Param("nombre") String nombre);

	/**
	 * Busca personas por combinación de nombres y apellidos
	 * @param primerNombre Primer nombre (opcional)
	 * @param segundoNombre Segundo nombre (opcional)
	 * @param primerApellido Primer apellido (opcional)
	 * @param segundoApellido Segundo apellido (opcional)
	 * @return Lista de personas que coinciden con los criterios
	 */
	@Query("SELECT p FROM Persona p WHERE " +
			"(:primerNombre IS NULL OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :primerNombre, '%'))) AND " +
			"(:segundoNombre IS NULL OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :segundoNombre, '%'))) AND " +
			"(:primerApellido IS NULL OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :primerApellido, '%'))) AND " +
			"(:segundoApellido IS NULL OR LOWER(p.nombre) LIKE LOWER(CONCAT('%', :segundoApellido, '%')))")
	List<Persona> findByFullNameCriteria(
			@Param("primerNombre") String primerNombre,
			@Param("segundoNombre") String segundoNombre,
			@Param("primerApellido") String primerApellido,
			@Param("segundoApellido") String segundoApellido
	);
}
