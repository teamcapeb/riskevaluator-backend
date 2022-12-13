package fr.capeb.backend.riskevaluator.repository;


import fr.capeb.backend.riskevaluator.model.Metier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MetierRepository extends JpaRepository<Metier, Integer> {

    //@Query("select distinct metierQuestion.metier from  MetierQuestionEntity metierQuestion where metierQuestion.question.categorieQuestion.questionnaire.idQuestionnaire = :aQuestionnaireId order by metierQuestion.metier.nomMetier")
    //List<Metier> getMetiersByQuestionnaireId(@Param("aQuestionnaireId") Integer aQuestionnaireId);
}
