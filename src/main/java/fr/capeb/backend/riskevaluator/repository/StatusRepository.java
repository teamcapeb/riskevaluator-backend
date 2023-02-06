package fr.capeb.backend.riskevaluator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.capeb.backend.riskevaluator.model.Status;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {

}

