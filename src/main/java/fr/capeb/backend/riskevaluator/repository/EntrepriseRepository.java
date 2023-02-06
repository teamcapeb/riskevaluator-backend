package fr.capeb.backend.riskevaluator.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.capeb.backend.riskevaluator.model.Entreprise;

@Repository
public interface EntrepriseRepository extends JpaRepository<Entreprise, Long> {

}
