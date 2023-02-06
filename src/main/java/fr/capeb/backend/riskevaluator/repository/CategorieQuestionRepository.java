package fr.capeb.backend.riskevaluator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.capeb.backend.riskevaluator.model.CategorieQuestion;

@Repository
public interface CategorieQuestionRepository extends JpaRepository<CategorieQuestion, Integer> {

}
