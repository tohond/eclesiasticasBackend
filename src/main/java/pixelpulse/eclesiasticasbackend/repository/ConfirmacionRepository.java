package pixelpulse.eclesiasticasbackend.repository;

import pixelpulse.eclesiasticasbackend.model.Acta;
import pixelpulse.eclesiasticasbackend.model.Confirmacion;
import pixelpulse.eclesiasticasbackend.model.Persona;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface ConfirmacionRepository extends JpaRepository<Confirmacion, Long> {
    


    @Query("SELECT c FROM Confirmacion c WHERE " +
            "c.confirmante = :persona OR " +
            "c.padrino = :persona OR " +
            "c.madrina = :persona")
     List<Confirmacion> findAllByPersonaInAnyRole(@Param("persona") Persona persona);

	 Confirmacion findByActa(Acta acta);
	
	
}
