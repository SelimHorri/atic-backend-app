package tn.cita.app.service.impl;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.domain.entity.VerificationToken;
import tn.cita.app.dto.notif.MailBodyContentBuilder;
import tn.cita.app.dto.notif.MailNotification;
import tn.cita.app.dto.request.RegisterRequest;
import tn.cita.app.dto.response.RegisterResponse;
import tn.cita.app.exception.wrapper.ExpiredVerificationTokenException;
import tn.cita.app.exception.wrapper.IllegalRegistrationRoleTypeException;
import tn.cita.app.exception.wrapper.PasswordNotMatchException;
import tn.cita.app.exception.wrapper.UsernameAlreadyExistsException;
import tn.cita.app.exception.wrapper.VerificationTokenNotFoundException;
import tn.cita.app.mapper.CustomerMapper;
import tn.cita.app.mapper.EmployeeMapper;
import tn.cita.app.repository.CredentialRepository;
import tn.cita.app.repository.CustomerRepository;
import tn.cita.app.repository.EmployeeRepository;
import tn.cita.app.repository.VerificationTokenRepository;
import tn.cita.app.service.RegistrationService;
import tn.cita.app.util.NotificationUtil;
import tn.cita.app.util.RegistrationUtils;

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
	
	/**
	 * @param RegisterRequest
	 * @return RegisterResponse
	 * This method contains 7 steps to achieve registration
	 * 
	 * Step1: check Role validation
	 * 
	 * Step2: check username duplication!
	 * 
	 * Step3: check password confirmation 
	 * 
	 * Step4: encode valid password and save User
	 * 
	 * Step5: check role type and process to the right roletype
	 */
	@Override
	public RegisterResponse register(final RegisterRequest registerRequest) {
		
		log.info("** RegistrationServiceImpl; RegisterResponse; register service...*\n");
		
		// Step1
		if (!RegistrationUtils.isCustomerRole(registerRequest.getRole())
				&& !RegistrationUtils.isWorkerRole(registerRequest.getRole())
				&& !RegistrationUtils.isManagerRole(registerRequest.getRole())
				&& !RegistrationUtils.isOwnerRole(registerRequest.getRole()))
			throw new IllegalRegistrationRoleTypeException("Wrong role type for registration, "
					+ "it should be Customer/Worker/Manager/Owner role");
		log.info("** User role checked successfully! *\n");
		
		// Step2
		this.credentialRepository.findByUsernameIgnoreCase(registerRequest.getUsername()).ifPresent((c) -> {
			throw new UsernameAlreadyExistsException(String
					.format("Account with username: %s already exists", c.getUsername()));
		});
		log.info("** User not exist by username checked successfully! *\n");
		
		// Step3
		if (!registerRequest.getPassword().equals(registerRequest.getConfirmPassword()))
			throw new PasswordNotMatchException("Unmatched passwords! please check again");
		log.info("** User password confirmation checked successfully! *\n");
		
		// Step4
		registerRequest.setPassword(this.passwordEncoder.encode(registerRequest.getConfirmPassword()));
		log.info("** User password encrypted successfully! *\n");
		
		// Step 5
		if (RegistrationUtils.isCustomerRole(registerRequest.getRole()))
			return this.registerCustomer(registerRequest);
		else if (RegistrationUtils.isWorkerRole(registerRequest.getRole())
				|| RegistrationUtils.isManagerRole(registerRequest.getRole())
				|| RegistrationUtils.isOwnerRole(registerRequest.getRole()))
			return this.registerEmployee(registerRequest);
		else 
			return null;
	}
	
	private RegisterResponse registerCustomer(final RegisterRequest registerRequest) {
		
		log.info("** Register Customer process... ! *\n");
		
		final var savedCustomer = this.customerRepository.save(CustomerMapper.map(registerRequest));
		log.info("** Customer saved successfully! *\n");
		
		final var verificationToken = new VerificationToken(UUID.randomUUID().toString(), 
				LocalDateTime.now().plusMinutes(AppConstant.EXPIRES_AT_FROM_NOW), 
				savedCustomer.getCredential());
		final var savedVerificationToken = this.verificationTokenRepository.save(verificationToken);
		log.info("** Verification token saved successfully! *\n");
		
		this.notificationUtil.sendMail(new MailNotification(savedCustomer.getEmail(), 
				"Registration", 
				new MailBodyContentBuilder(savedCustomer.getCredential().getUsername(), 
						String.format("%s/%s", 
								ServletUriComponentsBuilder.fromCurrentRequestUri().build(), 
								savedVerificationToken.getToken()))));
		log.info("** Mail sent successfully to: {}! *\n", savedCustomer.getEmail());
		
		return new RegisterResponse(String
				.format("User with username %s has been saved successfully. "
						+ "Check your email to enbale your account. "
						+ "Please consider that link will expire after %dmin from registration", 
						savedCustomer.getCredential().getUsername(), 
						AppConstant.EXPIRES_AT_FROM_NOW));
	}
	
	private RegisterResponse registerEmployee(final RegisterRequest registerRequest) {
		
		log.info("** Register Employee process... ! *\n");
		
		final var savedEmployee = this.employeeRepository.save(EmployeeMapper.map(registerRequest));
		log.info("** Employee saved successfully! *\n");
		
		final var verificationToken = new VerificationToken(UUID.randomUUID().toString(), 
				LocalDateTime.now().plusMinutes(AppConstant.EXPIRES_AT_FROM_NOW), 
				// AppConstant.EXPIRES_AT_FROM_NOW, 
				savedEmployee.getCredential());
		final var savedVerificationToken = this.verificationTokenRepository.save(verificationToken);
		log.info("** Verification token saved successfully! *\n");
		
		this.notificationUtil.sendMail(new MailNotification(savedEmployee.getEmail(), 
				"Registration", 
				new MailBodyContentBuilder(savedEmployee.getCredential().getUsername(), 
						String.format("%s/%s", 
								ServletUriComponentsBuilder.fromCurrentRequestUri().build(), 
								savedVerificationToken.getToken()))));
		log.info("** Mail sent successfully to {}! *\n", savedEmployee.getEmail());
		
		return new RegisterResponse(String
				.format("User with username %s has been saved successfully. "
						+ "Check your email to enbale your account. "
						+ "Please consider that link will expire after %dmin from registration", 
						savedEmployee.getCredential().getUsername(), 
						AppConstant.EXPIRES_AT_FROM_NOW));
	}
	
	@Override
	public String validateToken(final String token) {
		
		log.info("** RegistrationServiceImpl; String; validate registration token service...*\n");
		
		// fetch verificationToken by provided token
		final var verificationToken = this.verificationTokenRepository.findByToken(token)
				.orElseThrow(() -> new VerificationTokenNotFoundException(String
						.format("Link has been disactivated")));
		
		// check if token expired => if expired, then flush out token and throw exception
		if (verificationToken.getExpireDate().isEqual(LocalDateTime.now()) 
				|| verificationToken.getExpireDate().isBefore(LocalDateTime.now())) {
			
			// TODO: instead of just removing the verification token, we need to remove the whole user chain on cascade: User->Credential->VerificationToken
			this.verificationTokenRepository.deleteByToken(token);
			throw new ExpiredVerificationTokenException("Verification token has been expired");
		}
		
		// activate user
		final var credential = verificationToken.getCredential();
		credential.setIsEnabled(true);
		verificationToken.setCredential(credential);
		this.verificationTokenRepository.save(verificationToken);
		log.info("** User enabled successfully! *\n");
		
		// token should be deleted also after activating user to prevent reaccess to url token
		this.verificationTokenRepository.deleteByToken(token);
		log.info("** User token has been deleted! *\n");
		
		return "User has been activated successfully, go and login!";
	}
	
	
	
}















