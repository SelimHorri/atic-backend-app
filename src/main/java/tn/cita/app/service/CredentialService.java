package tn.cita.app.service;

import tn.cita.app.dto.CredentialDto;

public interface CredentialService {
	
	CredentialDto findByUsername(final String username);
	
}











