package tn.cita.app.mapper;

import javax.validation.constraints.NotNull;

import tn.cita.app.domain.entity.Credential;
import tn.cita.app.domain.entity.VerificationToken;
import tn.cita.app.dto.CredentialDto;
import tn.cita.app.dto.VerificationTokenDto;

public interface VerificationTokenMapper {
	
	public static VerificationTokenDto map(@NotNull final VerificationToken verificationToken) {
		return VerificationTokenDto.builder()
				.id(verificationToken.getId())
				.identifier(verificationToken.getIdentifier())
				.token(verificationToken.getToken())
				.expireDate(verificationToken.getExpireDate())
				.credentialDto(
					CredentialDto.builder()
						.id(verificationToken.getCredential().getId())
						.identifier(verificationToken.getCredential().getIdentifier())
						.username(verificationToken.getCredential().getUsername())
						.password(verificationToken.getCredential().getPassword())
						.isEnabled(verificationToken.getCredential().getIsEnabled())
						.isAccountNonExpired(verificationToken.getCredential().getIsAccountNonExpired())
						.isAccountNonLocked(verificationToken.getCredential().getIsAccountNonLocked())
						.isCredentialsNonExpired(verificationToken.getCredential().getIsCredentialsNonExpired())
						.build())
				.build();
	}
	
	public static VerificationToken map(@NotNull final VerificationTokenDto verificationTokenDto) {
		return VerificationToken.builder()
				.id(verificationTokenDto.getId())
				.identifier(verificationTokenDto.getIdentifier())
				.token(verificationTokenDto.getToken())
				.expireDate(verificationTokenDto.getExpireDate())
				.credential(
					Credential.builder()
						.id(verificationTokenDto.getCredentialDto().getId())
						.identifier(verificationTokenDto.getCredentialDto().getIdentifier())
						.username(verificationTokenDto.getCredentialDto().getUsername())
						.password(verificationTokenDto.getCredentialDto().getPassword())
						.isEnabled(verificationTokenDto.getCredentialDto().getIsEnabled())
						.isAccountNonExpired(verificationTokenDto.getCredentialDto().getIsAccountNonExpired())
						.isAccountNonLocked(verificationTokenDto.getCredentialDto().getIsAccountNonLocked())
						.isCredentialsNonExpired(verificationTokenDto.getCredentialDto().getIsCredentialsNonExpired())
						.build())
				.build();
	}
	
	
	
}














