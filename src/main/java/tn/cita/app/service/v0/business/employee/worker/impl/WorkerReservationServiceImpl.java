package tn.cita.app.service.v0.business.employee.worker.impl;

import java.util.List;
import java.util.Optional;

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
	public List<TaskDto> getAllReservations(final String username) {
		return this.taskService.findAllByWorkerId(this.employeeService.findByUsername(username).getId());
	}
	
	@Override
	public Page<TaskDto> getAllReservations(final String username, final ClientPageRequest clientPageRequest) {
		if (Optional.ofNullable(clientPageRequest).isPresent())
			return this.taskService.findAllByWorkerId(this.employeeService.findByUsername(username).getId(), clientPageRequest);
		else
			return new PageImpl<>(this.getAllReservations(username));
	}
	
	
	
}

















