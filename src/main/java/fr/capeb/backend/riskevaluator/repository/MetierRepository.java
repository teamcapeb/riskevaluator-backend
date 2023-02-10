package fr.capeb.backend.riskevaluator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fr.capeb.backend.riskevaluator.model.Metier;
import fr.capeb.backend.riskevaluator.projection.MetierScoreProjection;

@Repository
public interface MetierRepository extends JpaRepository<Metier, Integer> {
	
	Metier findTopByOrderByIdMetierDesc();
	
	@Query(value = "SELECT m.nom_metier as nomMetier, q.thematique , AVG(e.score_generale) AS scoreMoyen " +
			"FROM evaluation e " +
			"JOIN evaluations_metiers em " +
			"ON e.id_evaluation = em.evaluation_id " +
			"JOIN metier m " +
			"ON m.id_metier = em.metier_id " +
			"join score_category sc on sc.id_evaluation = e.id_evaluation" +
			"join categorie_question cq on cq.id_categorie = sc.id_categorie " +
			"join questionnaire q on q.id_questionnaire = cq.id_questionnaire " +
			"GROUP BY m.nom_metier, q.thematique " +
			"ORDER BY m.nom_metier", nativeQuery = true)
	List<MetierScoreProjection> findScoreForAllMetiers();
	
}
