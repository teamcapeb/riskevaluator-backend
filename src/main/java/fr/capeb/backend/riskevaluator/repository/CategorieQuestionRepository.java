package fr.capeb.backend.riskevaluator.repository;

import fr.capeb.backend.riskevaluator.model.CategorieQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategorieQuestionRepository extends JpaRepository<CategorieQuestion,Integer> {
}
