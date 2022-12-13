package fr.capeb.backend.riskevaluator.repository;

import fr.capeb.backend.riskevaluator.model.CategorieQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionCategorieRepository extends JpaRepository<CategorieQuestion, Integer> {


    //@Query("select cq from CategorieQuestionEntity cq where cq.questionnaire.idQuestionnaire = :idQ")
    // List<CategorieQuestion> findByQuestionaire(@Param("idQ") Integer idQ);

}
