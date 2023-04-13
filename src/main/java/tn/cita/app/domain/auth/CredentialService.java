package tn.cita.app.domain.auth;

import tn.cita.app.model.dto.CredentialDto;

public interface CredentialService {
	
	CredentialDto findById(final Integer id);
	CredentialDto findByIdentifier(final String identifier);
	CredentialDto findByUsername(final String username);
	
}






