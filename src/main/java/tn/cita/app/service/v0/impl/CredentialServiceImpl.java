package tn.cita.app.service.v0.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.dto.CredentialDto;
import tn.cita.app.exception.wrapper.CredentialNotFoundException;
import tn.cita.app.mapper.CredentialMapper;
import tn.cita.app.repository.CredentialRepository;
import tn.cita.app.service.v0.CredentialService;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class CredentialServiceImpl implements CredentialService {
	
	private final CredentialRepository credentialRepository;
	
	@Override
	public CredentialDto findById(final Integer id) {
		log.info("** CredentialServiceImpl; CredentialDto; find user by id service...*\n");
		return this.credentialRepository.findById(id)
				.map(CredentialMapper::map)
				.orElseThrow(() -> new CredentialNotFoundException(String
						.format("Credential with id %d not found", id)));
	}
	
	@Override
	public CredentialDto findByUsername(final String username) {
		log.info("** CredentialServiceImpl; CredentialDto; find user by username service...*\n");
		return this.credentialRepository.findByUsernameIgnoreCase(username)
				.map(CredentialMapper::map)
				.orElseThrow(() -> new CredentialNotFoundException(String
						.format("Credential with username %s not found", username)));
	}
	
	
	
}
















