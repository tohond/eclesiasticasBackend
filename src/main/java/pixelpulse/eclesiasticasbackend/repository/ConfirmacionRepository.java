package pixelpulse.eclesiasticasbackend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pixelpulse.eclesiasticasbackend.model.Confirmacion;

public interface ConfirmacionRepository extends JpaRepository<Confirmacion, String> {
}