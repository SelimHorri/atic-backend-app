package tn.cita.app.service.v0;

import tn.cita.app.dto.CredentialDto;
import tn.cita.app.repository.CredentialRepository;

public interface CredentialService {
	
	CredentialRepository getCredentialRepository();
	CredentialDto findById(final Integer id);
	CredentialDto findByUsername(final String username);
	
}












