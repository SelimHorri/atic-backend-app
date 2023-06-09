package tn.cita.app.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import tn.cita.app.exception.wrapper.VerificationTokenNotFoundException;
import tn.cita.app.model.domain.UserRoleBasedAuthority;
import tn.cita.app.model.domain.entity.Credential;
import tn.cita.app.model.domain.entity.VerificationToken;
import tn.cita.app.model.dto.CredentialDto;
import tn.cita.app.model.dto.VerificationTokenDto;
import tn.cita.app.repository.VerificationTokenRepository;
import tn.cita.app.service.VerificationTokenService;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class VerificationTokenServiceImplTest {
	
	@Autowired
	private VerificationTokenService verificationTokenService;
	
	@MockBean
	private VerificationTokenRepository verificationTokenRepository;
	
	@Test
	void givenValidToken_whenFindByToken_thenVerificationTokenDtoShouldBeFound() {
		
		final var token = "c856b457-ed66-4dd4-bc1a-f0be552a28e5";
		
		final var verificationToken = VerificationToken.builder()
				.id(null)
				.token(token)
				.expireDate(LocalDateTime.of(2023, 11, 6, 10, 50, 9))
				.credential(
					Credential.builder()
						.id(null)
						.username("jamesbond")
						.userRoleBasedAuthority(UserRoleBasedAuthority.CUSTOMER)
						.isEnabled(true)
						.isAccountNonExpired(true)
						.isAccountNonLocked(true)
						.isCredentialsNonExpired(true)
						.build())
				.build();
		
		when(this.verificationTokenRepository.findByToken(token))
				.thenReturn(Optional.ofNullable(verificationToken));
		
		final var expectedVerificationTokenDto = VerificationTokenDto.builder()
				.id(null)
				.token(token)
				.expireDate(LocalDateTime.of(2023, 11, 6, 10, 50, 9))
				.credentialDto(
					CredentialDto.builder()
						.id(null)
						.username("jamesbond")
						.userRoleBasedAuthority(UserRoleBasedAuthority.CUSTOMER)
						.isEnabled(true)
						.isAccountNonExpired(true)
						.isAccountNonLocked(true)
						.isCredentialsNonExpired(true)
						.build())
				.build();
		
		final var verificationTokenDto = this.verificationTokenService.findByToken(expectedVerificationTokenDto.getToken());
		
		assertThat(verificationTokenDto)
				.isNotNull()
				.isInstanceOf(VerificationTokenDto.class);
		assertThat(verificationTokenDto.getToken()).isEqualTo(expectedVerificationTokenDto.getToken());
		assertThat(verificationTokenDto.getExpireDate()).isEqualTo(expectedVerificationTokenDto.getExpireDate());
		assertThat(verificationTokenDto.getCredentialDto())
				.isNotNull()
				.isInstanceOf(CredentialDto.class);
		assertThat(verificationTokenDto.getCredentialDto().getUsername())
				.isEqualTo(expectedVerificationTokenDto.getCredentialDto().getUsername());
		assertThat(verificationTokenDto.getCredentialDto().getIsEnabled()).isTrue();
		assertThat(verificationTokenDto.getCredentialDto().getIsAccountNonExpired()).isTrue();
		assertThat(verificationTokenDto.getCredentialDto().getIsAccountNonLocked()).isTrue();
		assertThat(verificationTokenDto.getCredentialDto().getIsCredentialsNonExpired()).isTrue();
	}
	
	@Test
	void givenInValidToken_whenFindByToken_thenVerificationTokenNotFoundShouldBeThrown() {
		
		final var wrongToken = "c856b457-ed66-4dd4-bc1a-f0be552a28e5" + "dfvjlqerjf";
		
		final var verificationTokenNotFoundException = assertThrows(VerificationTokenNotFoundException.class, 
				() -> this.verificationTokenService.findByToken(wrongToken));
		
		assertThat(verificationTokenNotFoundException)
				.isNotNull()
				.isInstanceOf(VerificationTokenNotFoundException.class);
		assertThat(verificationTokenNotFoundException.getMessage())
				.isNotBlank()
				.startsWith("VerificationToken ")
				.endsWith(" not found")
				.isEqualTo(String.format("VerificationToken with token: %s is not found", wrongToken));
	}
	
	@Test
	void givenValidToken_whenDeleteByToken_thenShouldReturnTrue() {
		
		final var token = "c856b457-ed66-4dd4-bc1a-f0be552a28e5";
		doNothing().when(this.verificationTokenRepository)
				.deleteByToken(token);
		
		final boolean deleteByToken = this.verificationTokenService.deleteByToken(token);
		assertThat(deleteByToken).isTrue();
	}
	
}





