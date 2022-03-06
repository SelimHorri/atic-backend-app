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
import tn.cita.app.exception.wrapper.VerificationTokenNotFoundException;
import tn.cita.app.mapper.CustomerMapper;
import tn.cita.app.repository.CustomerRepository;
import tn.cita.app.repository.VerificationTokenRepository;
import tn.cita.app.service.RegistrationService;
import tn.cita.app.util.NotificationUtil;

@Service
@Transactional
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
	
	private final CustomerRepository customerRepository;
	// private final EmployeeService employeeService;
	private final VerificationTokenRepository verificationTokenRepository;
	private final PasswordEncoder passwordEncoder;
	private final NotificationUtil notificationUtil;
	
	@Override
	public RegisterResponse registerCustomer(final RegisterRequest registerRequest) {
		
		if (!registerRequest.getRole().equalsIgnoreCase(UserRoleBasedAuthority.CUSTOMER.name()))
			throw new IllegalRegistrationRoleTypeException("Wrong role type for registration, it should be Customer role");
		
		// TODO: check username duplication!
		
		if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword()))
			throw new PasswordNotMatchException("Unmatched passwords! please check again");
		
		registerRequest.setPassword(this.passwordEncoder.encode(registerRequest.getConfirmPassword()));
		final var savedCustomer = this.customerRepository.save(CustomerMapper.map(registerRequest));
		
		System.err.println(savedCustomer);
		
		// TODO: create verification token dto to be persisted
		final var verificationToken = new VerificationToken(UUID.randomUUID().toString(), 
				AppConstant.EXPIRES_AT_FROM_NOW, 
				savedCustomer.getCredential());
		final var savedVerificationToken = this.verificationTokenRepository.save(verificationToken);
		
		System.err.println(savedVerificationToken);
		
		// TODO: send email with saved verification token
		final Boolean isMailSent = this.notificationUtil.sendMail(new MailNotification(AppConstant.MAIL_SOURCE, savedCustomer.getEmail(), 
				"Registration", 
				String.format("Hi %s, \nClick this link to activate your account: %s/%s", 
						savedVerificationToken.getCredential().getUsername(), 
						ServletUriComponentsBuilder.fromCurrentRequestUri().build(), 
						savedVerificationToken.getToken())));
		
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
		
		final var verificationToken = this.verificationTokenRepository.findByToken(token)
				.orElseThrow(() -> new VerificationTokenNotFoundException(String
						.format("Link has been disactivated")));
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















