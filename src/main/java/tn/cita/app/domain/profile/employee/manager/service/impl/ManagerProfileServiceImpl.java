package tn.cita.app.domain.profile.employee.manager.service.impl;

import java.util.stream.Collectors;

import org.springframework.data.domain.PageImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.domain.profile.employee.manager.model.ManagerProfileRequest;
import tn.cita.app.domain.profile.employee.manager.model.ManagerProfileResponse;
import tn.cita.app.domain.profile.employee.manager.service.ManagerProfileService;
import tn.cita.app.exception.wrapper.EmployeeNotFoundException;
import tn.cita.app.exception.wrapper.PasswordNotMatchException;
import tn.cita.app.exception.wrapper.UsernameAlreadyExistsException;
import tn.cita.app.mapper.CategoryMapper;
import tn.cita.app.mapper.EmployeeMapper;
import tn.cita.app.mapper.ReservationMapper;
import tn.cita.app.mapper.SaloonTagMapper;
import tn.cita.app.mapper.ServiceDetailMapper;
import tn.cita.app.model.dto.EmployeeDto;
import tn.cita.app.repository.CategoryRepository;
import tn.cita.app.repository.EmployeeRepository;
import tn.cita.app.repository.ReservationRepository;
import tn.cita.app.repository.SaloonTagRepository;
import tn.cita.app.repository.ServiceDetailRepository;

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
		final var managerDto = this.employeeRepository
				.findByCredentialUsernameIgnoringCase(username.strip())
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
				.findByCredentialUsernameIgnoringCase(managerProfileRequest.username().strip()).ifPresent(c -> {
					if (!c.getCredential().getUsername().equals(managerProfileRequest.authenticatedUsername()))
						throw new UsernameAlreadyExistsException("Username already exists, please choose another");
		});
		
		if (!managerProfileRequest.password().equals(managerProfileRequest.confirmPassword()))
			throw new PasswordNotMatchException("Passwords are not matched.. please confirm");
		
		final var authenticatedManager = this.employeeRepository
				.findByCredentialUsernameIgnoringCase(managerProfileRequest.authenticatedUsername().strip())
					.orElseThrow(() -> new EmployeeNotFoundException("Manager not found"));
		authenticatedManager.setFirstname(managerProfileRequest.firstname().strip());
		authenticatedManager.setLastname(managerProfileRequest.lastname().strip());
		authenticatedManager.setEmail(managerProfileRequest.email().strip());
		authenticatedManager.setPhone(managerProfileRequest.phone().strip());
		authenticatedManager.setBirthdate(managerProfileRequest.birthdate());
		authenticatedManager.setHiredate(managerProfileRequest.hiredate());
		authenticatedManager.getCredential().setUsername(managerProfileRequest.username().strip().toLowerCase());
		authenticatedManager.getCredential()
				.setPassword(this.passwordEncoder.encode(managerProfileRequest.password()));
		
		return EmployeeMapper.map(this.employeeRepository.save(authenticatedManager));
	}
	
}








