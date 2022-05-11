package tn.cita.app.mapper;

import javax.validation.constraints.NotNull;

import tn.cita.app.domain.entity.Credential;
import tn.cita.app.domain.entity.VerificationToken;
import tn.cita.app.dto.VerificationTokenDto;

public interface VerificationTokenMapper {
	
	public static VerificationTokenDto map(@NotNull final VerificationToken verificationToken) {
		return VerificationTokenDto.builder()
				.id(verificationToken.getId())
				.token(verificationToken.getToken())
				.expireDate(verificationToken.getExpireDate())
				.credentialId(verificationToken.getCredential().getId())
				.build();
	}
	
	public static VerificationToken map(@NotNull final VerificationTokenDto verificationTokenDto) {
		return VerificationToken.builder()
				.id(verificationTokenDto.getId())
				.token(verificationTokenDto.getToken())
				.expireDate(verificationTokenDto.getExpireDate())
				.credential(
					Credential.builder()
						.id(verificationTokenDto.getCredentialId())
						.build())
				.build();
	}
	
	
	
}














