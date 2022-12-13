package fr.capeb.backend.riskevaluator.repository;

import fr.capeb.backend.riskevaluator.model.PreconisationGlobale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreconisationGlobaleRepository extends JpaRepository<PreconisationGlobale, Integer> {

    //@Query("select pg from PreconisationGlobaleEntity pg where pg.questionnaire.idQuestionnaire = :idQ")
    //List<PreconisationGlobale> findByQuestionaire(@Param("idQ") Integer idQ);
}
