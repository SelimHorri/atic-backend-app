package tn.cita.app.business.reservation.employee.worker.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.business.reservation.employee.worker.service.WorkerReservationService;
import tn.cita.app.exception.wrapper.EmployeeNotFoundException;
import tn.cita.app.mapper.EmployeeMapper;
import tn.cita.app.mapper.TaskMapper;
import tn.cita.app.model.dto.TaskDto;
import tn.cita.app.model.dto.request.ClientPageRequest;
import tn.cita.app.repository.EmployeeRepository;
import tn.cita.app.repository.TaskRepository;
import tn.cita.app.util.ClientPageRequestUtils;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class WorkerReservationServiceImpl implements WorkerReservationService {
	
	private final EmployeeRepository employeeRepository;
	private final TaskRepository taskRepository;
	
	@Override
	public Page<TaskDto> fetchAllReservations(final String username, final ClientPageRequest clientPageRequest) {
		log.info("** Fetch all paged reservations by worker.. *\n");
		return this.taskRepository.findAllByWorkerId(this.employeeRepository
					.findByCredentialUsernameIgnoringCase(username.strip())
					.map(EmployeeMapper::toDto)
					.orElseThrow(() -> new EmployeeNotFoundException(String
							.format("Employee with username: %s not found", username))).getId(), 
					ClientPageRequestUtils.from(clientPageRequest))
				.map(TaskMapper::toDto);
	}
	
	@Override
	public Page<TaskDto> fetchAllReservations(final String username) {
		log.info("** Fetch all reservations by worker.. *\n");
		final var workerDto = this.employeeRepository
					.findByCredentialUsernameIgnoringCase(username.strip())
					.map(EmployeeMapper::toDto)
					.orElseThrow(() -> new EmployeeNotFoundException(String
							.format("Employee with username: %s not found", username)));
		return new PageImpl<>(this.taskRepository
					.findAllByWorkerId(workerDto.getId()))
				.map(TaskMapper::toDto);
	}
	
	@Override
	public Page<TaskDto> searchAllLikeKey(final String username, final String key) {
		log.info("** Search all reservations like key by worker.. *\n");
		final var workerDto = this.employeeRepository
				.findByCredentialUsernameIgnoringCase(username.strip())
				.map(EmployeeMapper::toDto)
				.orElseThrow(() -> new EmployeeNotFoundException(String
						.format("Employee with username: %s not found", username)));
		return new PageImpl<>(this.taskRepository
				.searchAllByWorkerIdLikeKey(workerDto.getId(), key.strip().toLowerCase()).stream()
					.map(TaskMapper::toDto)
					.toList());
	}
	
}



