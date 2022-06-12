package tn.cita.app.domain.listener;

import javax.persistence.PrePersist;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import tn.cita.app.domain.entity.Credential;

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













