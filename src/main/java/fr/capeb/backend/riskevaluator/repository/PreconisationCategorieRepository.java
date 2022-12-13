package fr.capeb.backend.riskevaluator.repository;

import fr.capeb.backend.riskevaluator.model.PreconisationCategorie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PreconisationCategorieRepository extends JpaRepository<PreconisationCategorie, Integer> {
}
