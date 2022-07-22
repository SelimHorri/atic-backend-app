package tn.cita.app.service.v0.business.employee.worker.impl;

import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.TaskDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.mapper.TaskMapper;
import tn.cita.app.service.v0.EmployeeService;
import tn.cita.app.service.v0.TaskService;
import tn.cita.app.service.v0.business.employee.worker.WorkerReservationService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WorkerReservationServiceImpl implements WorkerReservationService {
	
	private final EmployeeService employeeService;
	private final TaskService taskService;
	
	@Override
	public Page<TaskDto> getAllReservations(final String username, final ClientPageRequest clientPageRequest) {
		return this.taskService.findAllByWorkerId(this.employeeService
				.findByCredentialUsername(username).getId(), clientPageRequest);
	}
	
	@Override
	public Page<TaskDto> getAllReservations(final String username) {
		return new PageImpl<>(this.taskService.findAllByWorkerId(this.employeeService
				.findByCredentialUsername(username).getId()));
	}
	
	@Override
	public Page<TaskDto> searchAllLikeKey(final String username, final String key) {
		final var workerDto = this.employeeService.findByCredentialUsername(username);
		return new PageImpl<>(this.taskService.geTaskRepository()
				.searchAllByWorkerIdLikeKey(workerDto.getId(), key.toLowerCase()).stream()
					.map(TaskMapper::map)
					.distinct()
					.collect(Collectors.toUnmodifiableList()));
	}
	
	
	
}














