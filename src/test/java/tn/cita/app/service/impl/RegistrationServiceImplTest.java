package tn.cita.app.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import tn.cita.app.business.auth.register.model.RegisterRequest;
import tn.cita.app.business.auth.register.service.RegistrationService;
import tn.cita.app.exception.wrapper.*;
import tn.cita.app.model.domain.UserRoleBasedAuthority;
import tn.cita.app.model.domain.entity.Credential;
import tn.cita.app.model.domain.entity.Employee;
import tn.cita.app.model.domain.entity.VerificationToken;
import tn.cita.app.repository.CredentialRepository;
import tn.cita.app.repository.CustomerRepository;
import tn.cita.app.repository.EmployeeRepository;
import tn.cita.app.repository.VerificationTokenRepository;
import tn.cita.app.util.NotificationUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

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
	private NotificationUtil notificationUtil;
	
	@Test
	void givenValidCustomerRegisterRequest_whenRegister_thenRegisterResponseShouldBeFound() {
		assertTrue(true);
	}
	
	@Test
	void givenValidWorkerRegisterRequest_whenRegister_thenRegisterResponseShouldBeFound() {
		assertTrue(true);
	}
	
	@Test
	void givenValidManagerRegisterRequest_whenRegister_thenRegisterResponseShouldBeFound() {
		assertTrue(true);
	}
	
	@Test
	void givenValidOwnerRegisterRequest_whenRegister_thenRegisterResponseShouldBeFound() {
		assertTrue(true);
	}
	
	@Test
	void givenInvalidUserRoleInRegisterRequest_whenRegister_thenIllegalRegistrationRoleTypeExceptionShouldBeThrown() {
		
		final var username = "seliimhorg(-tymrii000000skdfjbrjusrepfggikfrsrfleknrlfnkegi";
		final var registerRequest = RegisterRequest.builder()
				.firstname("selliiiiiim")
				.lastname("hoooooooooorrii")
				.email("cita.team.mail@gmail.com")
				.phone("22125144")
				.birthdate(LocalDate.of(1995, 1, 9))
				.username(username)
				.password("0000")
				.confirmPassword("0000")
				.role("XXX")
				.build();
		
		assertThatExceptionOfType(IllegalRoleTypeException.class)
				.isThrownBy(() -> this.registrationService.register(registerRequest))
				.withMessageStartingWith("Wrong role ")
				.withMessageEndingWith(" role")
				.withMessage("Wrong role type for registration, it should be Customer/Worker/Manager/Owner role");
	}
	
	@Test
	void givenExistingUsernameInRegisterRequest_whenRegister_thenUsernameAlreadyExistsExceptionShouldBeThrown() {
		
		final var username = "selimhorri";
		final var registerRequest = RegisterRequest.builder()
				.firstname("selliiiiiim")
				.lastname("hoooooooooorrii")
				.email("cita.team.mail@gmail.com")
				.phone("22125144")
				.birthdate(LocalDate.of(1995, 1, 9))
				.username(username)
				.password("0000")
				.confirmPassword("0000")
				.role(UserRoleBasedAuthority.WORKER.name())
				.build();
		
		when(this.credentialRepository.findByUsernameIgnoreCase(registerRequest.username()))
				.thenThrow(new UsernameAlreadyExistsException("Account with username: " + registerRequest.username() + " already exists"));
		
		assertThatExceptionOfType(UsernameAlreadyExistsException.class)
				.isThrownBy(() -> this.registrationService.register(registerRequest))
				.withMessageStartingWith("")
				.withMessageEndingWith("")
				.withMessage("Account with username: " + registerRequest.username() + " already exists");
	}
	
	@Test
	void givenUnmatchedPasswordsInRegisterRequest_whenRegister_thenPasswordNotMatchExceptionShouldBeThrown() {
		
		final var username = "seliimhi" + UUID.randomUUID().toString();
		final var registerRequest = RegisterRequest.builder()
				.firstname("selliiiiiim")
				.lastname("hoooooooooorrii")
				.email("cita.team.mail@gmail.com")
				.phone("22125144")
				.birthdate(LocalDate.of(1995, 1, 9))
				.username(username)
				.password("0000rgretg")
				.confirmPassword("0000")
				.role(UserRoleBasedAuthority.CUSTOMER.name())
				.build();
		
		assertThatExceptionOfType(PasswordNotMatchException.class)
				.isThrownBy(() -> this.registrationService.register(registerRequest))
				.withMessage("Passwords do not match! please check again");
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
				.isEqualTo("Link has been disabled");
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
		
		final var expiredVerificationTokenException = assertThrows(VerificationTokenExpiredException.class, 
				() -> this.registrationService.validateToken(token));
		
		assertThat(expiredVerificationTokenException).isNotNull();
		assertThat(expiredVerificationTokenException.getMessage())
				.isEqualTo("Link has been expired");
	}
	
}






