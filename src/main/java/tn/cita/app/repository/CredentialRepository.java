package tn.cita.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.cita.app.model.domain.UserRoleBasedAuthority;
import tn.cita.app.model.domain.entity.Credential;

import java.util.List;
import java.util.Optional;

public interface CredentialRepository extends JpaRepository<Credential, Integer> {
	
	Optional<Credential> findByIdentifier(final String identifier);
	Optional<Credential> findByUsernameIgnoreCase(final String username);
	List<Credential> findAllByIsEnabled(final boolean isEnabled);
	List<Credential> findAllByIsEnabledAndUserRoleBasedAuthority(final boolean isEnabled, final UserRoleBasedAuthority role);
	
}



