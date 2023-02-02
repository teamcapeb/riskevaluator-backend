package fr.capeb.backend.riskevaluator.repository;

import fr.capeb.backend.riskevaluator.model.Evaluation;
import fr.capeb.backend.riskevaluator.model.Metier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EvaluationRepository extends JpaRepository<Evaluation,Integer> {
    Evaluation findTopByOrderByIdEvaluationDesc();
}
