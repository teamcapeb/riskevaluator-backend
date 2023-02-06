package fr.capeb.backend.riskevaluator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.capeb.backend.riskevaluator.model.ScoreCategory;
import fr.capeb.backend.riskevaluator.model.ScoreCategoryPK;

@Repository
public interface ScoreCategorieRepository extends JpaRepository<ScoreCategory, ScoreCategoryPK> {

}
