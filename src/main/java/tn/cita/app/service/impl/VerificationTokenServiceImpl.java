package tn.cita.app.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.dto.VerificationTokenDto;
import tn.cita.app.exception.wrapper.VerificationTokenNotFoundException;
import tn.cita.app.mapper.VerificationTokenMapper;
import tn.cita.app.repository.VerificationTokenRepository;
import tn.cita.app.service.VerificationTokenService;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class VerificationTokenServiceImpl implements VerificationTokenService {
	
	private final VerificationTokenRepository verificationTokenRepository;
	
	@Transactional(readOnly = true)
	@Override
	public VerificationTokenDto findByToken(final String token) {
		log.info("** VerificationTokenServiceImpl; VerificationTokenDto; find by token service...*\n");
		return this.verificationTokenRepository.findByToken(token)
				.map(VerificationTokenMapper::map)
				.orElseThrow(() -> new VerificationTokenNotFoundException(String
						.format("VerificationToken with token: %s is not found", token)));
	}
	
	@Override
	public boolean deleteByToken(final String token) {
		log.info("** VerificationTokenServiceImpl; boolean; delete by token service...*\n");
		this.verificationTokenRepository.deleteByToken(token);
		return true;
	}
	
	
	
}















