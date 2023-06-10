package tn.cita.app.model.domain.listener;

import jakarta.persistence.PrePersist;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tn.cita.app.model.domain.entity.Credential;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CredentialEntityListener {
	
	@PrePersist
	public void preCreate(final Credential credential) {
		credential.setIsEnabled(false);
		credential.setIsAccountNonExpired(true);
		credential.setIsAccountNonLocked(true);
		credential.setIsCredentialsNonExpired(true);
	}
	
}



