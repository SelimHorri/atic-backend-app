package tn.cita.app.service.v0.business.employee.worker.impl;

import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.response.WorkerProfileResponse;
import tn.cita.app.service.v0.EmployeeService;
import tn.cita.app.service.v0.TaskService;
import tn.cita.app.service.v0.business.employee.worker.WorkerProfileService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WorkerProfileServiceImpl implements WorkerProfileService {
	
	private final EmployeeService employeeService;
	private final TaskService taskService;
	
	@Override
	public WorkerProfileResponse getProfile(final String username) {
		final var workerDto = this.employeeService.findByUsername(username);
		return new WorkerProfileResponse(
				workerDto, 
				workerDto.getCredentialDto(), 
				new PageImpl<>(this.taskService.findAllByWorkerId(workerDto.getId())));
	}
	
	
	
}













