package fr.capeb.backend.riskevaluator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.capeb.backend.riskevaluator.model.Metier;

@Repository
public interface MetierRepository extends JpaRepository<Metier, Integer> {
	
	Metier findTopByOrderByIdMetierDesc();
	
	//@Query("select distinct metierQuestion.metier from  MetierQuestionEntity metierQuestion where metierQuestion.question.categorieQuestion.questionnaire.idQuestionnaire = :aQuestionnaireId order by metierQuestion.metier.nomMetier")
	//List<Metier> getMetiersByQuestionnaireId(@Param("aQuestionnaireId") Integer aQuestionnaireId);
}
