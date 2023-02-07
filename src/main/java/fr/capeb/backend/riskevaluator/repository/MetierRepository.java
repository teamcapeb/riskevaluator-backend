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
	
	@Query(value = "select m.nom_metier as nomMetier, avg(e.score_generale) as scoreMoyen " +
			"from evaluation e join evaluations_metiers em " +
			"on e.id_evaluation = em.evaluation_id " +
			"join metier m on m.id_metier  = em.metier_id " +
			"group by m.nom_metier " +
			"order by m.nom_metier", nativeQuery = true)
	List<MetierScoreProjection> findScoreByMetier();
	
}
