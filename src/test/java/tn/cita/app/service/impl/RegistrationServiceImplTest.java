package tn.cita.app.service.impl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;

import tn.cita.app.domain.UserRoleBasedAuthority;
import tn.cita.app.domain.entity.Credential;
import tn.cita.app.domain.entity.Employee;
import tn.cita.app.domain.entity.VerificationToken;
import tn.cita.app.exception.wrapper.ExpiredVerificationTokenException;
import tn.cita.app.exception.wrapper.VerificationTokenNotFoundException;
import tn.cita.app.repository.CredentialRepository;
import tn.cita.app.repository.CustomerRepository;
import tn.cita.app.repository.EmployeeRepository;
import tn.cita.app.repository.VerificationTokenRepository;
import tn.cita.app.service.RegistrationService;
import tn.cita.app.util.NotificationUtil;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class RegistrationServiceImplTest {
	
	@Autowired
	private RegistrationService registrationService;
	
	@MockBean
	private CustomerRepository customerRepository;
	
	@MockBean
	private EmployeeRepository employeeRepository;
	
	@MockBean
	private CredentialRepository credentialRepository;
	
	@MockBean
	private VerificationTokenRepository verificationTokenRepository;
	
	@MockBean
	private PasswordEncoder passwordEncoder;
	
	@MockBean
	private NotificationUtil notificationUtil;
	
	@Test
	void givenValidCustomerRegisterRequest_whenRegister_thenRegisterResponseShouldBeFound() {
		
	}
	
	@Test
	void givenValidWorkerRegisterRequest_whenRegister_thenRegisterResponseShouldBeFound() {
		
	}
	
	@Test
	void givenValidManagerRegisterRequest_whenRegister_thenRegisterResponseShouldBeFound() {
		
	}
	
	@Test
	void givenValidOwnerRegisterRequest_whenRegister_thenRegisterResponseShouldBeFound() {
		
	}
	
	@Test
	void givenInvalidUserRoleInRegisterRequest_whenRegister_thenIllegalRegistrationRoleTypeExceptionShouldBeThrown() {
		
	}
	
	@Test
	void givenExistingUsernameInRegisterRequest_whenRegister_thenUsernameAlreadyExistsExceptionShouldBeThrown() {
		
	}
	
	@Test
	void givenUnmatchedPasswordsInRegisterRequest_whenRegister_thenPasswordNotMatchExceptionShouldBeThrown() {
		
	}
	
	@Test
	void givenValidToken_whenValidateToken_thenConfirmationMsgShouldBeReturned() {
		
		final var token = "c856b457-ed66-4dd4-bc1a-f0be552a28e5";
		
		final var verificationToken = VerificationToken.builder()
				.id(null)
				.token(token)
				.expireDate(LocalDateTime.of(2023, 11, 26, 10, 50, 9))
				.credential(
					Credential.builder()
						.id(null)
						.username("jamesbond")
						.userRoleBasedAuthority(UserRoleBasedAuthority.CUSTOMER)
						.isEnabled(true)
						.isAccountNonExpired(true)
						.isAccountNonLocked(true)
						.isCredentialsNonExpired(true)
						.employee(
								Employee.builder()
									.build())
						.build())
				.build();
		
		when(this.verificationTokenRepository.findByToken(verificationToken.getToken()))
				.thenReturn(Optional.ofNullable(verificationToken));
		doNothing().when(this.verificationTokenRepository)
				.deleteByToken(verificationToken.getToken());
		when(this.verificationTokenRepository.save(verificationToken))
				.thenReturn(verificationToken);
		
		final var validateToken = this.registrationService.validateToken(verificationToken.getToken());
		
		assertThat(validateToken)
				.isNotBlank()
				.isEqualTo("User has been activated successfully, go and login!");
	}
	
	@Test
	void givenInvalidToken_whenValidateToken_thenVerificationTokenNotFoundExceptionShouldBeThrown() {
		
		final var wrongToken = "c856b457-ed66-4dd4-bc1a-f0be552a28e5" + UUID.randomUUID().toString();
		when(this.verificationTokenRepository.findByToken(wrongToken))
				.thenReturn(Optional.empty());
		
		final var verificationTokenNotFoundException = assertThrows(VerificationTokenNotFoundException.class, 
				() -> this.registrationService.validateToken(wrongToken));
		
		assertThat(verificationTokenNotFoundException).isNotNull();
		assertThat(verificationTokenNotFoundException.getMessage())
				.startsWith("Link ")
				.endsWith(" disactivated")
				.isEqualTo("Link has been disactivated");
	}
	
	@Test
	void givenExpiredToken_whenValidateToken_thenExpiredVerificationTokenExceptionShouldBeThrown() {
		
		final var token = "c856b457-ed66-4dd4-bc1a-f0be552a28e5";
		
		final var verificationToken = VerificationToken.builder()
				.id(null)
				.token(token)
				.expireDate(LocalDateTime.of(2020, 1, 26, 10, 50, 9))
				.credential(
					Credential.builder()
						.id(null)
						.username("jamesbond")
						.userRoleBasedAuthority(UserRoleBasedAuthority.CUSTOMER)
						.isEnabled(true)
						.isAccountNonExpired(true)
						.isAccountNonLocked(true)
						.isCredentialsNonExpired(true)
						.employee(
								Employee.builder()
									.build())
						.build())
				.build();
		
		when(this.verificationTokenRepository.findByToken(verificationToken.getToken()))
				.thenReturn(Optional.ofNullable(verificationToken));
		doNothing().when(this.verificationTokenRepository)
				.deleteByToken(verificationToken.getToken());
		
		final var expiredVerificationTokenException = assertThrows(ExpiredVerificationTokenException.class, 
				() -> this.registrationService.validateToken(token));
		
		assertThat(expiredVerificationTokenException).isNotNull();
		assertThat(expiredVerificationTokenException.getMessage())
				.startsWith("Verification ")
				.endsWith(" expired")
				.isEqualTo("Verification token has been expired");
	}
	
	
	
}
















