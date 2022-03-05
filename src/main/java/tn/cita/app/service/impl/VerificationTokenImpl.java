package tn.cita.app.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.VerificationTokenDto;
import tn.cita.app.exception.wrapper.VerificationTokenNotFoundException;
import tn.cita.app.mapper.VerificationTokenMapper;
import tn.cita.app.repository.VerificationTokenRepository;
import tn.cita.app.service.VerificationTokenService;

@Service
@Transactional
@RequiredArgsConstructor
public class VerificationTokenImpl implements VerificationTokenService {
	
	private final VerificationTokenRepository verificationTokenRepository;
	
	@Transactional(readOnly = true)
	@Override
	public VerificationTokenDto findByToken(final String token) {
		return this.verificationTokenRepository.findByToken(token)
				.map(VerificationTokenMapper::map)
				.orElseThrow(() -> new VerificationTokenNotFoundException(String
						.format("VerificationToken with token: %s is not found", token)));
	}
	
	@Override
	public VerificationTokenDto save(final VerificationTokenDto verificationTokenDto) {
		return VerificationTokenMapper.map(this.verificationTokenRepository.save(VerificationTokenMapper.map(verificationTokenDto)));
	}
	
	
	
}















