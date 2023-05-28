package tn.cita.app.business.auth.register.service.impl;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.UUID;
import java.util.function.Supplier;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.business.auth.register.model.RegisterRequest;
import tn.cita.app.business.auth.register.model.RegisterResponse;
import tn.cita.app.business.auth.register.service.RegistrationService;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.exception.wrapper.IllegalRegistrationRoleTypeException;
import tn.cita.app.exception.wrapper.PasswordNotMatchException;
import tn.cita.app.exception.wrapper.UsernameAlreadyExistsException;
import tn.cita.app.exception.wrapper.VerificationTokenExpiredException;
import tn.cita.app.exception.wrapper.VerificationTokenNotFoundException;
import tn.cita.app.model.domain.UserRoleBasedAuthority;
import tn.cita.app.model.domain.entity.Credential;
import tn.cita.app.model.domain.entity.Customer;
import tn.cita.app.model.domain.entity.Employee;
import tn.cita.app.model.domain.entity.VerificationToken;
import tn.cita.app.model.dto.notif.MailBodyContentBuilder;
import tn.cita.app.model.dto.notif.MailNotification;
import tn.cita.app.repository.CredentialRepository;
import tn.cita.app.repository.CustomerRepository;
import tn.cita.app.repository.EmployeeRepository;
import tn.cita.app.repository.VerificationTokenRepository;
import tn.cita.app.util.NotificationUtil;
import tn.cita.app.util.UserRoleUtils;

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
	private final NotificationUtil notificationUtil;
	
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
		
		return this.commonProcess(savedCustomer.getCredential(), () -> savedCustomer.getCredential().getUserRoleBasedAuthority());
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
		
		return this.commonProcess(savedEmployee.getCredential(), () -> savedEmployee.getCredential().getUserRoleBasedAuthority());
	}
	
	private RegisterResponse commonProcess(final Credential credential, final Supplier<UserRoleBasedAuthority> selector) {
		final var verificationToken = new VerificationToken(
				UUID.randomUUID().toString(),
				LocalDateTime.now().plusMinutes(AppConstants.USER_EXPIRES_AFTER_MINUTES), 
				credential);
		final var savedVerificationToken = this.verificationTokenRepository.save(verificationToken);
		log.info("** Verification token saved successfully! *");
		
		if (selector.get().equals(UserRoleBasedAuthority.CUSTOMER)) {
			this.notificationUtil.sendMail(new MailNotification(
					credential.getCustomer().getEmail(),
					"Registration", 
					new MailBodyContentBuilder(
							credential.getUsername(),
							String.format("%s/%s", 
									ServletUriComponentsBuilder.fromCurrentRequestUri().build(), 
									savedVerificationToken.getToken()))));
			log.info("** Mail sent successfully to: {}! *", credential.getCustomer().getEmail());
		}
		else {
			this.notificationUtil.sendMail(new MailNotification(
					credential.getEmployee().getEmail(),
					"Registration", 
					new MailBodyContentBuilder(
							credential.getUsername(),
							String.format("%s/%s",
									ServletUriComponentsBuilder.fromCurrentRequestUri().build(),
									savedVerificationToken.getToken()))));
			log.info("** Mail sent successfully to: {}! *", credential.getEmployee().getEmail());
		}
		
		return new RegisterResponse("""
				User with username %s has been saved successfully.
				Check your email to enable your account.
				Please consider that link will expire after %dmin from registration.
				""".formatted(credential.getUsername(), AppConstants.USER_EXPIRES_AFTER_MINUTES));
	}
	
}









