package tn.cita.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.cita.app.model.domain.entity.VerificationToken;

import java.util.Optional;

public interface VerificationTokenRepository extends JpaRepository<VerificationToken, Integer> {
	
	Optional<VerificationToken> findByIdentifier(final String identifier);
	Optional<VerificationToken> findByToken(final String token);
	void deleteByToken(final String token);
	
}



