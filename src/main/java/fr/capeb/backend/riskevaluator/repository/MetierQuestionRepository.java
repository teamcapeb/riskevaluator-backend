package fr.capeb.backend.riskevaluator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.capeb.backend.riskevaluator.model.MetierQuestion;
import fr.capeb.backend.riskevaluator.model.MetierQuestionPK;

@Repository
public interface MetierQuestionRepository extends JpaRepository<MetierQuestion, MetierQuestionPK> {

}
