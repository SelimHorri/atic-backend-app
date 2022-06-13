package tn.cita.app.service.v0.business.employee.worker.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.TaskDto;
import tn.cita.app.dto.request.ClientPageRequest;
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
		return this.taskService.findAllByWorkerId(this.employeeService.findByUsername(username).getId(), clientPageRequest);
	}
	
	@Override
	public Page<TaskDto> getAllReservations(final String username) {
		return new PageImpl<>(this.taskService.findAllByWorkerId(this.employeeService.findByUsername(username).getId()));
	}
	
	
	
}

















