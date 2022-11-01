package tn.cita.app.service.v0.business.employee.worker.impl;

import org.springframework.data.domain.PageImpl;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.exception.wrapper.EmployeeNotFoundException;
import tn.cita.app.exception.wrapper.PasswordNotMatchException;
import tn.cita.app.exception.wrapper.UsernameAlreadyExistsException;
import tn.cita.app.mapper.EmployeeMapper;
import tn.cita.app.model.dto.EmployeeDto;
import tn.cita.app.model.dto.request.WorkerProfileRequest;
import tn.cita.app.model.dto.response.WorkerProfileResponse;
import tn.cita.app.repository.EmployeeRepository;
import tn.cita.app.service.v0.TaskService;
import tn.cita.app.service.v0.business.employee.worker.WorkerProfileService;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class WorkerProfileServiceImpl implements WorkerProfileService {
	
	private final EmployeeRepository employeeRepository;
	private final TaskService taskRepository;
	private final PasswordEncoder passwordEncoder;
	
	@Override
	public WorkerProfileResponse fetchProfile(final String username) {
		log.info("** Fetch worker profile.. *\n");
		final var workerDto = this.employeeRepository
				.findByCredentialUsernameIgnoringCase(username)
				.map(EmployeeMapper::map)
				.orElseThrow(() -> new EmployeeNotFoundException("Worker not found"));
		return new WorkerProfileResponse(
				workerDto, 
				workerDto.getCredentialDto(), 
				new PageImpl<>(this.taskRepository.findAllByWorkerId(workerDto.getId())));
	}
	
	@Transactional
	@Override
	public EmployeeDto updateProfileInfo(final WorkerProfileRequest workerProfileRequest) {
		
		log.info("** Update worker profile.. *\n");

		this.employeeRepository
				.findByCredentialUsernameIgnoringCase(workerProfileRequest.getUsername().strip()).ifPresent(c -> {
			if (!c.getCredential().getUsername().equals(workerProfileRequest.getAuthenticatedUsername()))
				throw new UsernameAlreadyExistsException("Username already exists, please choose another");
		});
		
		if (!workerProfileRequest.getPassword().equals(workerProfileRequest.getConfirmPassword()))
			throw new PasswordNotMatchException("Passwords are not matched.. please confirm");
		
		final var authenticatedWorker = this.employeeRepository
				.findByCredentialUsernameIgnoringCase(workerProfileRequest.getAuthenticatedUsername().strip())
				.orElseThrow(() -> new EmployeeNotFoundException("Worker not found"));
		authenticatedWorker.setFirstname(workerProfileRequest.getFirstname().strip());
		authenticatedWorker.setLastname(workerProfileRequest.getLastname().strip());
		authenticatedWorker.setEmail(workerProfileRequest.getEmail().strip());
		authenticatedWorker.setPhone(workerProfileRequest.getPhone().strip());
		authenticatedWorker.setBirthdate(workerProfileRequest.getBirthdate());
		authenticatedWorker.setHiredate(workerProfileRequest.getHiredate());
		authenticatedWorker.getCredential().setUsername(workerProfileRequest.getUsername().strip().toLowerCase());
		authenticatedWorker.getCredential().setPassword(this.passwordEncoder.encode(workerProfileRequest.getPassword()));
		
		return EmployeeMapper.map(this.employeeRepository.save(authenticatedWorker));
	}
	
	
	
}













