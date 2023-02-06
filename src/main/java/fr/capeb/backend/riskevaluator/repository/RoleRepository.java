package fr.capeb.backend.riskevaluator.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.capeb.backend.riskevaluator.model.Role;
import fr.capeb.backend.riskevaluator.model.enumeration.ERole;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	
	Optional<Role> findByName(ERole name);
}
