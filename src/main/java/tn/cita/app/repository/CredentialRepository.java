package tn.cita.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tn.cita.app.domain.entity.Credential;

public interface CredentialRepository extends JpaRepository<Credential, Integer> {
	
	Optional<Credential> findByIdentifier(final String identifier);
	Optional<Credential> findByUsernameIgnoreCase(final String username);
	
}








