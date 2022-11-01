package tn.cita.app.service.v0.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.exception.wrapper.VerificationTokenNotFoundException;
import tn.cita.app.mapper.VerificationTokenMapper;
import tn.cita.app.model.dto.VerificationTokenDto;
import tn.cita.app.repository.VerificationTokenRepository;
import tn.cita.app.service.v0.VerificationTokenService;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService {
	
	private final VerificationTokenRepository verificationTokenRepository;
	
	@Override
	public VerificationTokenDto findByToken(final String token) {
		log.info("** Find verificationToken by token.. *\n");
		return this.verificationTokenRepository.findByToken(token)
				.map(VerificationTokenMapper::map)
				.orElseThrow(() -> new VerificationTokenNotFoundException(String
						.format("VerificationToken with token: %s is not found", token)));
	}
	
	@Transactional
	@Override
	public boolean deleteByToken(final String token) {
		log.info("** Delete verificationToken by token..*\n");
		this.verificationTokenRepository.deleteByToken(token);
		return true;
	}
	
	
	
}















