package fr.capeb.backend.riskevaluator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.capeb.backend.riskevaluator.model.PreconisationGlobale;

@Repository
public interface PreconisationGlobaleRepository extends JpaRepository<PreconisationGlobale, Integer> {
	
	//@Query("select pg from PreconisationGlobaleEntity pg where pg.questionnaire.idQuestionnaire = :idQ")
	//List<PreconisationGlobale> findByQuestionaire(@Param("idQ") Integer idQ);
}
