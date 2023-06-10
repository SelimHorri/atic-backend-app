package tn.cita.app.model.domain.listener;

import jakarta.persistence.PrePersist;
import tn.cita.app.model.domain.entity.VerificationToken;

import java.util.UUID;

public class VerificationTokenEntityListener {
	
	@PrePersist
	public void preCreate(final VerificationToken verificationToken) {
		verificationToken.setToken(UUID.randomUUID().toString());
	}
	
}



