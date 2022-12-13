package fr.capeb.backend.riskevaluator.repository;

import fr.capeb.backend.riskevaluator.model.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
}

