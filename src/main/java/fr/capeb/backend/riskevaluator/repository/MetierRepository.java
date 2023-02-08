package fr.capeb.backend.riskevaluator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.capeb.backend.riskevaluator.model.Metier;
import fr.capeb.backend.riskevaluator.projection.MetierScoreProjection;

@Repository
public interface MetierRepository extends JpaRepository<Metier, Integer> {
	
	Metier findTopByOrderByIdMetierDesc();
	
	@Query(value = "SELECT m.nom_metier as nomMetier, COALESCE(AVG(e.score_generale), 0) AS scoreMoyen " +
			"FROM metier m " +
			"LEFT JOIN evaluations_metiers em " +
			"ON m.id_metier = em.metier_id " +
			"LEFT JOIN evaluation e " +
			"ON em.evaluation_id = e.id_evaluation " +
			"GROUP BY m.nom_metier " +
			"ORDER BY m.nom_metier", nativeQuery = true)
	List<MetierScoreProjection> findScoreByMetier();
	
}
