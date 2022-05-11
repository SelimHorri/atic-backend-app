package tn.cita.app.service;

import tn.cita.app.dto.CredentialDto;

public interface CredentialService {
	
	CredentialDto findById(final Integer id);
	CredentialDto findByUsername(final String username);
	
}












