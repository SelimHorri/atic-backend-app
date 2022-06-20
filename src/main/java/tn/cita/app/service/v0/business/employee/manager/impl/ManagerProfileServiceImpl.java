package tn.cita.app.service.v0.business.employee.manager.impl;

import java.util.List;

import org.springframework.data.domain.PageImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.EmployeeDto;
import tn.cita.app.dto.request.ManagerProfileRequest;
import tn.cita.app.dto.response.ManagerProfileResponse;
import tn.cita.app.exception.wrapper.EmployeeNotFoundException;
import tn.cita.app.exception.wrapper.PasswordNotMatchException;
import tn.cita.app.exception.wrapper.UsernameAlreadyExistsException;
import tn.cita.app.mapper.EmployeeMapper;
import tn.cita.app.service.v0.EmployeeService;
import tn.cita.app.service.v0.business.employee.manager.ManagerProfileService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ManagerProfileServiceImpl implements ManagerProfileService {
	
	private final EmployeeService employeeService;
	private final PasswordEncoder passwordEncoder;
	// private final SaloonService saloonService;
	// private final ReservationService reservationService;
	
	@Override
	public ManagerProfileResponse getProfile(final String username) {
		final var managerDto = this.employeeService.findByUsername(username);
		return new ManagerProfileResponse(
				managerDto, 
				managerDto.getCredentialDto(), 
				new PageImpl<>(List.of()));
	}
	
	@Transactional
	@Override
	public EmployeeDto updateProfileInfo(final ManagerProfileRequest managerProfileRequest) {
		
		this.employeeService.getEmployeeRepository()
				.findByCredentialUsernameIgnoringCase(managerProfileRequest.getUsername().strip()).ifPresent(c -> {
					if (!c.getCredential().getUsername().equals(managerProfileRequest.getAuthenticatedUsername()))
						throw new UsernameAlreadyExistsException("Username already exists, please choose another");
		});
		
		if (!managerProfileRequest.getPassword().equals(managerProfileRequest.getConfirmPassword()))
			throw new PasswordNotMatchException("Passwords are not matched.. please confirm");
		
		final var authenticatedManager = this.employeeService.getEmployeeRepository()
				.findByCredentialUsernameIgnoringCase(managerProfileRequest.getAuthenticatedUsername())
					.orElseThrow(() -> new EmployeeNotFoundException("Manager not found"));
		authenticatedManager.setFirstname(managerProfileRequest.getFirstname());
		authenticatedManager.setLastname(managerProfileRequest.getLastname());
		authenticatedManager.setEmail(managerProfileRequest.getEmail());
		authenticatedManager.setPhone(managerProfileRequest.getPhone());
		authenticatedManager.setBirthdate(managerProfileRequest.getBirthdate());
		authenticatedManager.setHiredate(managerProfileRequest.getHiredate());
		authenticatedManager.getCredential().setUsername(managerProfileRequest.getUsername().toLowerCase());
		authenticatedManager.getCredential()
				.setPassword(this.passwordEncoder.encode(managerProfileRequest.getPassword()));
		
		return EmployeeMapper.map(this.employeeService.getEmployeeRepository().save(authenticatedManager));
	}
	
	
	
}

















