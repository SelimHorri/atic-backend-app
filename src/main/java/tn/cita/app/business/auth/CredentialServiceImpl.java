package tn.cita.app.business.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.cita.app.exception.wrapper.CredentialNotFoundException;
import tn.cita.app.mapper.CredentialMapper;
import tn.cita.app.model.dto.CredentialDto;
import tn.cita.app.repository.CredentialRepository;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class CredentialServiceImpl implements CredentialService {
	
	private final CredentialRepository credentialRepository;
	
	@Override
	public CredentialDto findById(final Integer id) {
		log.info("** Find user by id .. *\n");
		return this.credentialRepository.findById(id)
				.map(CredentialMapper::toDto)
				.orElseThrow(() -> new CredentialNotFoundException("Credential not found"));
	}
	
	@Override
	public CredentialDto findByIdentifier(final String identifier) {
		return this.credentialRepository.findByIdentifier(identifier.strip())
				.map(CredentialMapper::toDto)
				.orElseThrow(() -> new CredentialNotFoundException("Credential not found"));
	}
	
	@Override
	public CredentialDto findByUsername(final String username) {
		log.info("** Find user by username.. *\n");
		return this.credentialRepository.findByUsernameIgnoreCase(username)
				.map(CredentialMapper::toDto)
				.orElseThrow(() -> new CredentialNotFoundException(String
						.format("Credential with username %s not found", username)));
	}
	
}









