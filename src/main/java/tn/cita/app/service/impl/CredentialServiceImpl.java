package tn.cita.app.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.CredentialDto;
import tn.cita.app.exception.wrapper.CredentialNotFoundException;
import tn.cita.app.mapper.CredentialMapper;
import tn.cita.app.repository.CredentialRepository;
import tn.cita.app.service.CredentialService;

@Service
@Transactional
@RequiredArgsConstructor
public class CredentialServiceImpl implements CredentialService {
	
	private final CredentialRepository credentialRepository;
	
	@Override
	public CredentialDto findByUsername(final String username) {
		return this.credentialRepository.findByUsernameIgnoreCase(username)
				.map(CredentialMapper::map)
				.orElseThrow(() -> new CredentialNotFoundException(String
						.format("Credential with username %s not found", username)));
	}
	
	
	
}















