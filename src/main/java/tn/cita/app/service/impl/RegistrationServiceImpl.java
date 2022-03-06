package tn.cita.app.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.domain.UserRoleBasedAuthority;
import tn.cita.app.domain.entity.VerificationToken;
import tn.cita.app.dto.notif.MailNotification;
import tn.cita.app.dto.request.RegisterRequest;
import tn.cita.app.dto.response.RegisterResponse;
import tn.cita.app.exception.wrapper.ExpiredVerificationTokenException;
import tn.cita.app.exception.wrapper.IllegalRegistrationRoleTypeException;
import tn.cita.app.exception.wrapper.MailNotificationNotProcessedException;
import tn.cita.app.exception.wrapper.PasswordNotMatchException;
import tn.cita.app.exception.wrapper.UsernameAlreadyExistsException;
import tn.cita.app.exception.wrapper.VerificationTokenNotFoundException;
import tn.cita.app.mapper.CustomerMapper;
import tn.cita.app.repository.CredentialRepository;
import tn.cita.app.repository.CustomerRepository;
import tn.cita.app.repository.VerificationTokenRepository;
import tn.cita.app.service.RegistrationService;
import tn.cita.app.util.NotificationUtil;

@Service
@Transactional
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
	
	private final CustomerRepository customerRepository;
	// private final EmployeeRepository employeeRepository;
	private final CredentialRepository credentialRepository;
	private final VerificationTokenRepository verificationTokenRepository;
	private final PasswordEncoder passwordEncoder;
	private final NotificationUtil notificationUtil;
	
	/**
	 * @param RegisterRequest
	 * @return RegisterResponse
	 * This method contains 7 steps to achieve registration
	 * Step1: check Role validation
	 * Step2: check username duplication!
	 * Step3: check password confirmation
	 * Step4: encode valid password and save User
	 * Step5: create verification token dto to be persisted for saved User
	 * Step6: send email with saved verification token
	 * Step7: check if mail sent or not
	 */
	@Override
	public RegisterResponse registerCustomer(final RegisterRequest registerRequest) {
		
		// Step1
		if (!registerRequest.getRole().equalsIgnoreCase(UserRoleBasedAuthority.CUSTOMER.name()))
			throw new IllegalRegistrationRoleTypeException("Wrong role type for registration, it should be Customer role");
		
		// Step2
		this.credentialRepository.findByUsernameIgnoreCase(registerRequest.getUsername()).ifPresent((c) -> {
			throw new UsernameAlreadyExistsException(String
					.format("Account with username: %s already exists", c.getUsername()));
		});
		
		// Step3
		if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword()))
			throw new PasswordNotMatchException("Unmatched passwords! please check again");
		
		// Step4
		registerRequest.setPassword(this.passwordEncoder.encode(registerRequest.getConfirmPassword()));
		final var savedCustomer = this.customerRepository.save(CustomerMapper.map(registerRequest));
		
		System.err.println(savedCustomer);
		
		// Step5
		final var verificationToken = new VerificationToken(UUID.randomUUID().toString(), 
				AppConstant.EXPIRES_AT_FROM_NOW, 
				savedCustomer.getCredential());
		final var savedVerificationToken = this.verificationTokenRepository.save(verificationToken);
		
		System.err.println(savedVerificationToken);
		
		// Step6
		final Boolean isMailSent = this.notificationUtil.sendMail(new MailNotification(AppConstant.MAIL_SOURCE, savedCustomer.getEmail(), 
				"Registration", 
				String.format("Hi %s, \nClick this link to activate your account: %s/%s", 
						savedVerificationToken.getCredential().getUsername(), 
						ServletUriComponentsBuilder.fromCurrentRequestUri().build(), 
						savedVerificationToken.getToken())));
		
		// Step7
		if (isMailSent != null && !isMailSent)
			throw new MailNotificationNotProcessedException("Mail not sent");
		
		return new RegisterResponse(isMailSent, String
				.format("Customer with username %s has been saved successfully", savedCustomer.getCredential().getUsername()));
	}
	
	@Override
	public RegisterResponse registerEmployee(final RegisterRequest registerRequest) {
		
		final boolean isUserRoleBasedAuthorityValid = (!registerRequest.getRole().equalsIgnoreCase(UserRoleBasedAuthority.EMPLOYEE.name()))
				|| (!registerRequest.getRole().equalsIgnoreCase(UserRoleBasedAuthority.MANAGER.name()))
				|| (!registerRequest.getRole().equalsIgnoreCase(UserRoleBasedAuthority.OWNER.name()));
		
		if (isUserRoleBasedAuthorityValid)
			throw new IllegalRegistrationRoleTypeException("Wrong role type for registration, should be Employee/Manager/Owner");
		if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword()))
			throw new PasswordNotMatchException("Unmatched passwords! please check again");
		
		
		
		return null;
	}
	
	@Override
	public String validateTokenCustmoer(final String token) {
		
		// fetch verificationToken by provided token
		final var verificationToken = this.verificationTokenRepository.findByToken(token)
				.orElseThrow(() -> new VerificationTokenNotFoundException(String
						.format("Link has been disactivated")));
		
		// check if token expired => if expired, then flush out token and throw exception
		if (verificationToken.getExpireDate().isEqual(LocalDateTime.now()) 
				|| verificationToken.getExpireDate().isBefore(LocalDateTime.now())) {
			
			this.verificationTokenRepository.deleteByToken(token);
			throw new ExpiredVerificationTokenException("Verification token has been expired");
		}
		
		// activate user
		var credential = verificationToken.getCredential();
		credential.setIsEnabled(true);
		verificationToken.setCredential(credential);
		this.verificationTokenRepository.save(verificationToken);
		
		// token should be deleted also after activating user to prevent reaccess to url token
		this.verificationTokenRepository.deleteByToken(token);
		
		return "User has been activated successfully, go and login!";
	}
	
	
	
}















