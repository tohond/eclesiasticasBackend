package pixelpulse.eclesiasticasbackend.repository;

import pixelpulse.eclesiasticasbackend.model.Matrimonio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatrimonioRepository extends JpaRepository<Matrimonio, String> {
}