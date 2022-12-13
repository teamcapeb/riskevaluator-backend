package fr.capeb.backend.riskevaluator.repository;

import fr.capeb.backend.riskevaluator.model.ScoreCategory;
import fr.capeb.backend.riskevaluator.model.ScoreCategoryPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScoreCategorieRepository extends JpaRepository<ScoreCategory, ScoreCategoryPK> {
}
