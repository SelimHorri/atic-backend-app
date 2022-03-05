package tn.cita.app.service.impl;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.domain.UserRoleBasedAuthority;
import tn.cita.app.dto.VerificationTokenDto;
import tn.cita.app.dto.notif.MailNotification;
import tn.cita.app.dto.request.RegisterRequest;
import tn.cita.app.dto.response.RegisterResponse;
import tn.cita.app.exception.wrapper.IllegalRegistrationRoleTypeException;
import tn.cita.app.exception.wrapper.MailNotificationNotProcessedException;
import tn.cita.app.exception.wrapper.PasswordNotMatchException;
import tn.cita.app.mapper.CustomerMapper;
import tn.cita.app.service.CustomerService;
import tn.cita.app.service.RegistrationService;
import tn.cita.app.service.VerificationTokenService;
import tn.cita.app.util.NotificationUtil;

@Service
@Transactional
@RequiredArgsConstructor
public class RegistrationServiceImpl implements RegistrationService {
	
	private final CustomerService customerService;
	// private final EmployeeService employeeService;
	private final VerificationTokenService verificationTokenService;
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
		final var savedCustomerDto = this.customerService.save(CustomerMapper.map(registerRequest));
		
		// TODO: create verification token dto to be persisted
		final var verificationTokenDto = new VerificationTokenDto(UUID.randomUUID().toString(), 
				LocalDateTime.now().plusMinutes(Duration.ofMinutes(30).toMinutes()), 
				savedCustomerDto.getCredentialDto());
		final var savedVerificationTokenDto = this.verificationTokenService.save(verificationTokenDto);
		
		// TODO: send email with saved verification token
		final boolean isMailSent = this.notificationUtil.sendMail(new MailNotification(AppConstant.MAIL_SOURCE, savedCustomerDto.getEmail(), null, 
				String.format("Verification token to validate your SignUp: %s/%s", 
						ServletUriComponentsBuilder.fromCurrentContextPath(), 
						savedVerificationTokenDto.getToken())));
		
		if (!isMailSent)
			throw new MailNotificationNotProcessedException("Mail not sent");
		
		return new RegisterResponse(isMailSent, String
				.format("Customer with username %s saved successfully", savedCustomerDto.getCredentialDto().getUsername()));
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
	
	
	
}















