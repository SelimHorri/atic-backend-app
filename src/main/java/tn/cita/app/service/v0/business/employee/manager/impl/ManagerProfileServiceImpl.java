package tn.cita.app.service.v0.business.employee.manager.impl;

import org.springframework.data.domain.PageImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.dto.EmployeeDto;
import tn.cita.app.dto.request.ManagerProfileRequest;
import tn.cita.app.dto.response.ManagerProfileResponse;
import tn.cita.app.exception.wrapper.EmployeeNotFoundException;
import tn.cita.app.exception.wrapper.PasswordNotMatchException;
import tn.cita.app.exception.wrapper.UsernameAlreadyExistsException;
import tn.cita.app.mapper.EmployeeMapper;
import tn.cita.app.service.v0.CategoryService;
import tn.cita.app.service.v0.EmployeeService;
import tn.cita.app.service.v0.ReservationService;
import tn.cita.app.service.v0.SaloonTagService;
import tn.cita.app.service.v0.ServiceDetailService;
import tn.cita.app.service.v0.business.employee.manager.ManagerProfileService;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class ManagerProfileServiceImpl implements ManagerProfileService {
	
	private final EmployeeService employeeService;
	private final PasswordEncoder passwordEncoder;
	private final ReservationService reservationService;
	private final SaloonTagService saloonTagService;
	private final CategoryService categoryService;
	private final ServiceDetailService serviceDetailService;
	
	@Override
	public ManagerProfileResponse fetchProfile(final String username) {
		log.info("** Fetch manager profile.. *\n");
		final var managerDto = this.employeeService.findByCredentialUsername(username);
		return new ManagerProfileResponse(
				managerDto, 
				new PageImpl<>(this.employeeService.findAllByManagerId(managerDto.getId())),
				new PageImpl<>(this.reservationService.findAllBySaloonId(managerDto.getSaloonDto().getId())),
				new PageImpl<>(this.saloonTagService.findAllBySaloonId(managerDto.getSaloonDto().getId())), 
				new PageImpl<>(this.categoryService.findAllBySaloonId(managerDto.getSaloonDto().getId())), 
				new PageImpl<>(this.serviceDetailService.findAllByCategorySaloonId(managerDto.getSaloonDto().getId())));
	}
	
	@Transactional
	@Override
	public EmployeeDto updateProfileInfo(final ManagerProfileRequest managerProfileRequest) {
		
		log.info("** Update manager profile.. *\n");
		
		this.employeeService.getEmployeeRepository()
				.findByCredentialUsernameIgnoringCase(managerProfileRequest.getUsername().strip()).ifPresent(c -> {
					if (!c.getCredential().getUsername().equals(managerProfileRequest.getAuthenticatedUsername()))
						throw new UsernameAlreadyExistsException("Username already exists, please choose another");
		});
		
		if (!managerProfileRequest.getPassword().equals(managerProfileRequest.getConfirmPassword()))
			throw new PasswordNotMatchException("Passwords are not matched.. please confirm");
		
		final var authenticatedManager = this.employeeService.getEmployeeRepository()
				.findByCredentialUsernameIgnoringCase(managerProfileRequest.getAuthenticatedUsername().strip())
					.orElseThrow(() -> new EmployeeNotFoundException("Manager not found"));
		authenticatedManager.setFirstname(managerProfileRequest.getFirstname().strip());
		authenticatedManager.setLastname(managerProfileRequest.getLastname().strip());
		authenticatedManager.setEmail(managerProfileRequest.getEmail().strip());
		authenticatedManager.setPhone(managerProfileRequest.getPhone().strip());
		authenticatedManager.setBirthdate(managerProfileRequest.getBirthdate());
		authenticatedManager.setHiredate(managerProfileRequest.getHiredate());
		authenticatedManager.getCredential().setUsername(managerProfileRequest.getUsername().strip().toLowerCase());
		authenticatedManager.getCredential()
				.setPassword(this.passwordEncoder.encode(managerProfileRequest.getPassword()));
		
		return EmployeeMapper.map(this.employeeService.getEmployeeRepository().save(authenticatedManager));
	}
	
	
	
}

















