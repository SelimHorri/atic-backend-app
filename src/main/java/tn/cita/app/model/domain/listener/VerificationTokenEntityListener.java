package tn.cita.app.model.domain.listener;

import java.util.UUID;

import jakarta.persistence.PrePersist;

import tn.cita.app.model.domain.entity.VerificationToken;

public class VerificationTokenEntityListener {
	
	@PrePersist
	public void preCreate(final VerificationToken verificationToken) {
		verificationToken.setToken(UUID.randomUUID().toString());
	}
	
}




