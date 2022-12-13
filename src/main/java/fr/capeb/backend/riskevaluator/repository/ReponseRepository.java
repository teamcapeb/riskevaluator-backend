package fr.capeb.backend.riskevaluator.repository;

import fr.capeb.backend.riskevaluator.model.Reponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReponseRepository extends JpaRepository<Reponse,Integer> {
}
