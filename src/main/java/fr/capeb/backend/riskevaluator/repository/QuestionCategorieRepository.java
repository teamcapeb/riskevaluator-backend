package fr.capeb.backend.riskevaluator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.capeb.backend.riskevaluator.model.CategorieQuestion;

@Repository
public interface QuestionCategorieRepository extends JpaRepository<CategorieQuestion, Integer> {
	
	//@Query("select cq from CategorieQuestionEntity cq where cq.questionnaire.idQuestionnaire = :idQ")
	// List<CategorieQuestion> findByQuestionaire(@Param("idQ") Integer idQ);
	
}
