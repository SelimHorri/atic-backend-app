package tn.cita.app.service.v0.business.employee.manager.impl;

import java.util.stream.Collectors;

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
import tn.cita.app.mapper.CategoryMapper;
import tn.cita.app.mapper.EmployeeMapper;
import tn.cita.app.mapper.ReservationMapper;
import tn.cita.app.mapper.SaloonTagMapper;
import tn.cita.app.mapper.ServiceDetailMapper;
import tn.cita.app.repository.CategoryRepository;
import tn.cita.app.repository.EmployeeRepository;
import tn.cita.app.repository.ReservationRepository;
import tn.cita.app.repository.SaloonTagRepository;
import tn.cita.app.repository.ServiceDetailRepository;
import tn.cita.app.service.v0.business.employee.manager.ManagerProfileService;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class ManagerProfileServiceImpl implements ManagerProfileService {
	
	private final EmployeeRepository employeeRepository;
	private final PasswordEncoder passwordEncoder;
	private final ReservationRepository reservationRepository;
	private final SaloonTagRepository saloonTagRepository;
	private final CategoryRepository categoryRepository;
	private final ServiceDetailRepository serviceDetailRepository;
	
	@Override
	public ManagerProfileResponse fetchProfile(final String username) {
		log.info("** Fetch manager profile.. *\n");
		final var managerDto = this.employeeRepository.findByCredentialUsernameIgnoringCase(username.strip())
				.map(EmployeeMapper::map)
				.orElseThrow(() -> new EmployeeNotFoundException(String
						.format("Employee with username: %s not found", username)));
		return new ManagerProfileResponse(
				managerDto, 
				new PageImpl<>(this.employeeRepository.findAllByManagerId(managerDto.getId()).stream()
						.map(EmployeeMapper::map)
						.distinct()
						.collect(Collectors.toUnmodifiableList())),
				new PageImpl<>(this.reservationRepository.findAllBySaloonId(managerDto.getSaloonDto().getId()).stream()
						.map(ReservationMapper::map)
						.distinct()
						.collect(Collectors.toUnmodifiableList())),
				new PageImpl<>(this.saloonTagRepository.findAllBySaloonId(managerDto.getSaloonDto().getId()).stream()
						.map(SaloonTagMapper::map)
						.distinct()
						.collect(Collectors.toUnmodifiableList())), 
				new PageImpl<>(this.categoryRepository.findAllBySaloonId(managerDto.getSaloonDto().getId()).stream()
						.map(CategoryMapper::map)
						.distinct()
						.collect(Collectors.toUnmodifiableList())), 
				new PageImpl<>(this.serviceDetailRepository.findAllByCategorySaloonId(managerDto.getSaloonDto().getId()).stream()
						.map(ServiceDetailMapper::map)
						.distinct()
						.collect(Collectors.toUnmodifiableList())));
	}
	
	@Transactional
	@Override
	public EmployeeDto updateProfileInfo(final ManagerProfileRequest managerProfileRequest) {
		
		log.info("** Update manager profile.. *\n");
		
		this.employeeRepository
				.findByCredentialUsernameIgnoringCase(managerProfileRequest.getUsername().strip()).ifPresent(c -> {
					if (!c.getCredential().getUsername().equals(managerProfileRequest.getAuthenticatedUsername()))
						throw new UsernameAlreadyExistsException("Username already exists, please choose another");
		});
		
		if (!managerProfileRequest.getPassword().equals(managerProfileRequest.getConfirmPassword()))
			throw new PasswordNotMatchException("Passwords are not matched.. please confirm");
		
		final var authenticatedManager = this.employeeRepository
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
		
		return EmployeeMapper.map(this.employeeRepository.save(authenticatedManager));
	}
	
	
	
}

















