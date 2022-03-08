package tn.cita.app.service;

import tn.cita.app.domain.entity.Credential;

public interface CredentialService {
	
	Credential findByUsername(final String username);
	
}












