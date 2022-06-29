package tn.cita.app.service.v0.business.employee.manager.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.response.ManagerWorkerAssignmentResponse;
import tn.cita.app.service.v0.EmployeeService;
import tn.cita.app.service.v0.TaskService;
import tn.cita.app.service.v0.business.employee.manager.ManagerWorkerAssignmentService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ManagerWorkerAssignmentServiceImpl implements ManagerWorkerAssignmentService {
	
	private final EmployeeService employeeService;
	private final TaskService taskService;
	
	@Override
	public ManagerWorkerAssignmentResponse getAllWorkerTasks(final String username, final Integer workerId, 
			final ClientPageRequest clientPageRequest) {
		final var managerDto = this.employeeService.findByUsername(username);
		return new ManagerWorkerAssignmentResponse(managerDto, 
				this.taskService.findAllByWorkerId(workerId, clientPageRequest));
	}
	
	
	
}











