package fr.capeb.backend.riskevaluator.repository;

import fr.capeb.backend.riskevaluator.model.Questionnaire;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface QuestionnaireRepository extends JpaRepository<Questionnaire, Integer> {


    //@Query("select cnt from QuestionnaireEntity cnt where cnt.thematique = :tm")
    //Optional<Questionnaire> findByThematique(@Param("tm") String tm);

    //@Query("select distinct categorieQuestion.questionnaire " +
    //  "from  MetierQuestionEntity metierQuestion join CategorieQuestionEntity categorieQuestion " +
    //   "on (metierQuestion.question.categorieQuestion.idCategorie=categorieQuestion.idCategorie) " +
    //  "where metierQuestion.metier.idMetier in (:metierIds)" +
    //   "order by categorieQuestion.questionnaire.thematique")
    // List<Questionnaire> getQuestionnaireByMetiersIds(@Param("metierIds") Set<Integer> metierIds);


}
