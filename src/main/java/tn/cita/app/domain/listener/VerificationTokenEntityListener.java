package tn.cita.app.domain.listener;

import java.util.UUID;

import javax.persistence.PrePersist;

import tn.cita.app.domain.entity.VerificationToken;

public class VerificationTokenEntityListener {
	
	@PrePersist
	public void preCreate(final VerificationToken verificationToken) {
		verificationToken.setToken(UUID.randomUUID().toString());
	}
	
	
	
}









