package tn.cita.app.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import tn.cita.app.domain.UserRoleBasedAuthority;
import tn.cita.app.domain.entity.Credential;
import tn.cita.app.domain.entity.Employee;
import tn.cita.app.exception.wrapper.CredentialNotFoundException;
import tn.cita.app.repository.CredentialRepository;
import tn.cita.app.service.CredentialService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CredentialServiceImplTest {
	
	@MockBean
	private CredentialRepository credentialRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private CredentialService credentialService;
	private Credential credential;
	
	@BeforeEach
	void setUp() throws Exception {
		
		final var username = "selimhorri";
		this.credential = Credential.builder()
				.id(null)
				.username(username)
				.password(this.passwordEncoder.encode("0000"))
				.userRoleBasedAuthority(UserRoleBasedAuthority.EMPLOYEE)
				.isEnabled(true)
				.employee(
						Employee.builder()
							.firstname("Selim")
							.lastname("Horri")
							.build())
				.build();
		
		when(this.credentialRepository.findByUsernameIgnoreCase(username))
				.thenReturn(Optional.of(this.credential));
	}
	
	@Test
	void givenValidUsername_whenFindCredentialByUsername_thenCredentialShouldBeFound() {
		
		final var credentialDtoExpected = this.credentialService.findByUsername(this.credential.getUsername());
		
		assertThat(credentialDtoExpected).isNotNull();
		assertThat(credentialDtoExpected.getUsername()).isEqualTo(this.credential.getUsername());
		assertThat(credentialDtoExpected.getPassword()).isEqualTo(this.credential.getPassword());
		assertThat(credentialDtoExpected.getIsEnabled()).isTrue();
	}
	
	@Test
	void givenInvalidUsername_whenFindCredentialByUsername_thenCredentialNotFoundExceptionShouldBeThrown() {
		
		final var newUsername = this.credential.getUsername() + "iii";
		final var credentialNotFoundException = assertThrows(CredentialNotFoundException.class, 
				() -> this.credentialService.findByUsername(newUsername));
		
		assertThat(credentialNotFoundException).isNotNull();
		assertThat(credentialNotFoundException.getMessage()).startsWith("Credential ");
		assertThat(credentialNotFoundException.getMessage()).endsWith(" not found");
		assertThat(credentialNotFoundException.getMessage()).isEqualTo(String
				.format("Credential with username %s not found", newUsername));
	}
	
	
	
}













