package fr.capeb.backend.riskevaluator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.capeb.backend.riskevaluator.model.Evaluation;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation, Integer> {
	
	Evaluation findTopByOrderByIdEvaluationDesc();
}
