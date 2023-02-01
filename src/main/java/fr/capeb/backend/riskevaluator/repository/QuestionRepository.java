package fr.capeb.backend.riskevaluator.repository;


import fr.capeb.backend.riskevaluator.model.CategorieQuestion;
import fr.capeb.backend.riskevaluator.model.Question;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {
    @Query("select distinct categorieQuestion " +
     "from  MetierQuestion metierQuestion " +
      "join CategorieQuestion categorieQuestion " +
        "on metierQuestion.question.categorieQuestion.idCategorie=categorieQuestion.idCategorie " +
         "where metierQuestion.question.categorieQuestion.questionnaire.idQuestionnaire = (:aQuestionnaireId) " +
         "and metierQuestion.metier.idMetier in (:metierIds) " +
        "order by categorieQuestion.libelle")
    List<CategorieQuestion> getCategorieQuestionsByQuestionnaireIdAndMetiers(@Param("aQuestionnaireId") Integer aQuestionnaireId,
			@Param("metierIds") Set<Integer> metierIds);

}
