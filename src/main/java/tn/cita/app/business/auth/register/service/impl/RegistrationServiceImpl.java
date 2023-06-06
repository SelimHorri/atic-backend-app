package tn.cita.app.business.auth.register.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import tn.cita.app.business.auth.register.model.RegisterRequest;
import tn.cita.app.business.auth.register.model.RegisterResponse;
import tn.cita.app.business.auth.register.service.RegistrationService;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.exception.wrapper.*;
import tn.cita.app.model.domain.UserRoleBasedAuthority;
import tn.cita.app.model.domain.entity.Credential;
import tn.cita.app.model.domain.entity.Customer;
import tn.cita.app.model.domain.entity.Employee;
import tn.cita.app.model.domain.entity.VerificationToken;
import tn.cita.app.model.dto.notif.MailNotification;
import tn.cita.app.repository.CredentialRepository;
import tn.cita.app.repository.CustomerRepository;
import tn.cita.app.repository.EmployeeRepository;
import tn.cita.app.repository.VerificationTokenRepository;
import tn.cita.app.util.NotificationUtil;
import tn.cita.app.util.UserRoleUtils;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
	
	private final CustomerRepository customerRepository;
	private final EmployeeRepository employeeRepository;
	private final CredentialRepository credentialRepository;
	private final VerificationTokenRepository verificationTokenRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Qualifier("mailNotificationUtil")
	private final NotificationUtil mailNotificationUtil;
	
	@Override
	public RegisterResponse register(final RegisterRequest registerRequest) {
		log.info("** Register..*");
		
		if (!isValidRole(registerRequest.role()))
			throw new IllegalRegistrationRoleTypeException("Wrong role type for registration, "
					+ "it should be Customer/Worker/Manager/Owner role");
		log.info("** User role checked successfully! *");
		
		this.credentialRepository
				.findByUsernameIgnoreCase(registerRequest.username()).ifPresent(c -> {
			throw new UsernameAlreadyExistsException("Account with username: %s already exists".formatted(c.getUsername()));
		});
		log.info("** User not exist by username checked successfully! *");
		
		if (!registerRequest.password().equals(registerRequest.confirmPassword()))
			throw new PasswordNotMatchException("Unmatched passwords! please check again");
		log.info("** User password confirmation checked successfully! *");
		
		return switch (UserRoleBasedAuthority.valueOf(registerRequest.role())) {
			case CUSTOMER -> this.registerCustomer(registerRequest);
			case WORKER, MANAGER, OWNER -> this.registerEmployee(registerRequest);
			case ADMIN -> null; //new RegisterResponse(false, null);
		};
	}
	
	@Override
	public String validateToken(final String token) {
		log.info("** Validate registration token..*");
		
		// fetch verificationToken by provided token
		final var verificationToken = this.verificationTokenRepository.findByToken(token)
				.orElseThrow(() -> new VerificationTokenNotFoundException("Link has been disabled"));
		
		// check if token expired => if expired, then flush out token and throw exception
		if (verificationToken.getExpireDate().isEqual(LocalDateTime.now()) 
				|| verificationToken.getExpireDate().isBefore(LocalDateTime.now())) {
			
			// TODO: instead of just removing the verification token, we need to remove the whole user chain on cascade: User->Credential->VerificationToken
			this.verificationTokenRepository.deleteByToken(token);
			throw new VerificationTokenExpiredException("Verification token has been expired");
		}
		
		// activate user
		final var credential = verificationToken.getCredential();
		credential.setIsEnabled(true);
		this.credentialRepository.save(credential);
		log.info("** User enabled successfully! *");
		
		// token should be deleted also after activating user to prevent reaccess to url token
		this.verificationTokenRepository.deleteByToken(token);
		log.info("** User token has been deleted! *");
		
		return "User has been activated successfully, go and login!";
	}
	
	@Override
	public RegisterResponse resendToken(final String username) {
		log.info("** resend token **");
		
		final var credential = this.credentialRepository.findByUsernameIgnoreCase(username)
				.orElseThrow(() -> new CredentialNotFoundException(
						"Account: %s is not registered".formatted(username)));
		
		if (credential.getIsEnabled())
			throw new BusinessException("Account: '%s' is already enabled, go and login"
					.formatted(credential.getUsername()));
		
		return this.sendAccountValidation(credential,
				ServletUriComponentsBuilder.fromCurrentRequestUri()
				/*
				.path("/{token}")
				*/
				);
	}
	
	@Override
	public String validateResentToken(final String token) {
		return null;
	}
	
	private static boolean isValidRole(final String role) {
		return Arrays.stream(UserRoleBasedAuthority.values())
				.map(UserRoleBasedAuthority::name)
				.anyMatch(roleName -> roleName.equals(role));
	}
	
	private RegisterResponse registerCustomer(final RegisterRequest registerRequest) {
		log.info("** Register Customer process... ! *");
		
		final var savedCustomer = this.customerRepository
				.save(Customer.builder()
						.firstname(registerRequest.firstname())
						.lastname(registerRequest.lastname())
						.email(registerRequest.email())
						.phone(registerRequest.phone())
						.birthdate(registerRequest.birthdate())
						.credential(
								Credential.builder()
									.username(registerRequest.username())
									.password(this.passwordEncoder
											.encode(registerRequest.password()))
									.userRoleBasedAuthority(UserRoleUtils
											.getUserRoleFrom(registerRequest.role()))
									.isEnabled(false)
									.isAccountNonExpired(true)
									.isAccountNonLocked(true)
									.isCredentialsNonExpired(true)
									.build())
						.build());
		log.info("** Customer saved successfully! *");
		
		return this.sendAccountValidation(savedCustomer.getCredential(),
				ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{token}"));
	}
	
	private RegisterResponse registerEmployee(final RegisterRequest registerRequest) {
		log.info("** Register Employee process... ! *");
		
		final var savedEmployee = this.employeeRepository
				.save(Employee.builder()
						.firstname(registerRequest.firstname())
						.lastname(registerRequest.lastname())
						.email(registerRequest.email())
						.phone(registerRequest.phone())
						.birthdate(registerRequest.birthdate())
						.credential(
								Credential.builder()
									.username(registerRequest.username())
									.password(this.passwordEncoder
											.encode(registerRequest.password()))
									.userRoleBasedAuthority(UserRoleUtils
											.getUserRoleFrom(registerRequest.role()))
									.isEnabled(false)
									.isAccountNonExpired(true)
									.isAccountNonLocked(true)
									.isCredentialsNonExpired(true)
									.build())
						.build());
		log.info("** Employee saved successfully! *");
		
		return this.sendAccountValidation(savedEmployee.getCredential(),
				ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{token}"));
	}
	
	private RegisterResponse sendAccountValidation(final Credential credential, final UriComponentsBuilder uriBuilder) {
		final var verificationToken = new VerificationToken(
				UUID.randomUUID().toString(),
				LocalDateTime.now().plusMinutes(AppConstants.USER_EXPIRES_AFTER_MINUTES),
				credential);
		final var savedVerificationToken = this.verificationTokenRepository.save(verificationToken);
		log.info("** Verification token saved successfully! *");
		
		this.sendValidationMail(savedVerificationToken, uriBuilder);
		
		return new RegisterResponse("""
				User with username %s has been saved successfully.
				Check your email to enable your account.
				Please consider that link will expire after %dmin from registration.
				""".formatted(credential.getUsername(), AppConstants.USER_EXPIRES_AFTER_MINUTES));
	}
	
	private void sendValidationMail(final VerificationToken verificationToken, final UriComponentsBuilder uriBuilder) {
		if (verificationToken.getCredential()
				.getUserRoleBasedAuthority().equals(UserRoleBasedAuthority.CUSTOMER)) {
			this.mailNotificationUtil.sendHtmlMail(new MailNotification(
					verificationToken.getCredential().getCustomer().getEmail(),
					"Registration",
					null),
					Map.of(
						"username", verificationToken.getCredential().getUsername(),
						"confirmLink", uriBuilder.build(verificationToken.getToken()).toString()));
			log.info("** Mail sent successfully to: {}! *", verificationToken.getCredential().getCustomer().getEmail());
		}
		else {
			this.mailNotificationUtil.sendHtmlMail(new MailNotification(
					verificationToken.getCredential().getEmployee().getEmail(),
					"Registration",
					null),
					Map.of(
						"username", verificationToken.getCredential().getUsername(),
						"confirmLink", uriBuilder.build(verificationToken.getToken()).toString()));
			log.info("** Mail sent successfully to: {}! *", verificationToken.getCredential().getEmployee().getEmail());
		}
	}
	
}





