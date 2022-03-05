package tn.cita.app.service;

import tn.cita.app.dto.VerificationTokenDto;

public interface VerificationTokenService {
	
	VerificationTokenDto findByToken(final String token);
	VerificationTokenDto save(final VerificationTokenDto verificationTokenDto);
	boolean deleteByToken(final String token);
	
}













