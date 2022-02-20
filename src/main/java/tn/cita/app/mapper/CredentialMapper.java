package tn.cita.app.mapper;

import javax.validation.constraints.NotNull;

import tn.cita.app.domain.entity.Credential;
import tn.cita.app.dto.CredentialDto;

public interface CredentialMapper {
	
	public static CredentialDto map(@NotNull final Credential credential) {
		return CredentialDto.builder()
				.id(credential.getId())
				.username(credential.getUsername())
				.password(credential.getPassword())
				.userRoleBasedAuthority(credential.getUserRoleBasedAuthority())
				.isEnabled(credential.getIsEnabled())
				.isAccountNonExpired(credential.getIsAccountNonExpired())
				.isAccountNonLocked(credential.getIsAccountNonLocked())
				.isCredentialsNonExpired(credential.getIsCredentialsNonExpired())
				.build();
	}
	
	public static Credential map(@NotNull final CredentialDto credentialDto) {
		return Credential.builder()
				.id(credentialDto.getId())
				.username(credentialDto.getUsername())
				.password(credentialDto.getPassword())
				.userRoleBasedAuthority(credentialDto.getUserRoleBasedAuthority())
				.isEnabled(credentialDto.getIsEnabled())
				.isAccountNonExpired(credentialDto.getIsAccountNonExpired())
				.isAccountNonLocked(credentialDto.getIsAccountNonLocked())
				.isCredentialsNonExpired(credentialDto.getIsCredentialsNonExpired())
				.build();
	}
	
	
	
}















