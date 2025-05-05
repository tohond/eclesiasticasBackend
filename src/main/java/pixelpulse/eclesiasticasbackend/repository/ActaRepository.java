package pixelpulse.eclesiasticasbackend.repository;


import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import pixelpulse.eclesiasticasbackend.model.Acta;

@Repository
public interface ActaRepository extends JpaRepository<Acta, UUID> {
	
	/*@Query(value = 
	           "SELECT a.id, a.numero_acta, 'BAUTIZO' as tipo, b.id as related_id " +
	           "FROM acta a INNER JOIN bautizo b ON a.id = b.numero_acta " +
	           "UNION ALL " +
	           "SELECT a.id, a.numero_acta, 'CONFIRMACION' as tipo, c.id as related_id " +
	           "FROM acta a INNER JOIN confirmacion c ON a.id = c.id_acta " +
	           "UNION ALL " +
	           "SELECT a.id, a.numero_acta, 'MATRIMONIO' as tipo, m.id as related_id " +
	           "FROM acta a INNER JOIN matrimonio m ON a.id = m.id_acta", 
	           nativeQuery = true) */
			List<Acta> findAll();

    
}
