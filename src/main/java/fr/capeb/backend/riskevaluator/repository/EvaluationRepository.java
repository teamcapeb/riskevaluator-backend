package fr.capeb.backend.riskevaluator.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import fr.capeb.backend.riskevaluator.model.Evaluation;
import fr.capeb.backend.riskevaluator.projection.CategorieEvaluationProjection;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Integer> {
	
	Evaluation findTopByOrderByIdEvaluationDesc();
	
	@Query(value = "SELECT cq.libelle, q.thematique, count(*) " +
			"FROM categorie_question cq " +
			"JOIN score_category sc " +
			"ON cq.id_categorie = sc.id_categorie " +
			"join questionnaire q " +
			"ON q.id_questionnaire = cq.id_questionnaire " +
			"GROUP BY cq.libelle, q.thematique " +
			"ORDER BY cq.libelle", nativeQuery = true)
	List<CategorieEvaluationProjection> getNumberOfEvaluationParCategorie();
	
	
}
