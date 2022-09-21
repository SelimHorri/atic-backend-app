package tn.cita.app.service.v0.business.employee.manager.impl;

import java.util.stream.Collectors;

import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.response.ManagerWorkerAssignmentResponse;
import tn.cita.app.mapper.TaskMapper;
import tn.cita.app.service.v0.EmployeeService;
import tn.cita.app.service.v0.TaskService;
import tn.cita.app.service.v0.business.employee.manager.ManagerWorkerAssignmentService;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class ManagerWorkerAssignmentServiceImpl implements ManagerWorkerAssignmentService {
	
	private final EmployeeService employeeService;
	private final TaskService taskService;
	
	@Override
	public ManagerWorkerAssignmentResponse fetchAllWorkerTasks(final String username, final Integer workerId, 
			final ClientPageRequest clientPageRequest) {
		log.info("** Fetch all worker tasks by manager.. *\n");
		final var managerDto = this.employeeService.findByCredentialUsername(username);
		return new ManagerWorkerAssignmentResponse(managerDto, 
				this.taskService.findAllByWorkerId(workerId, clientPageRequest));
	}
	
	@Override
	public ManagerWorkerAssignmentResponse searchAllLikeKey(final String username, final Integer workerId, final String key) {
		log.info("** Search all worker tasks like key by manager.. *\n");
		return new ManagerWorkerAssignmentResponse(
				this.employeeService.findByCredentialUsername(username), 
				new PageImpl<>(this.taskService.geTaskRepository()
						.searchAllByWorkerIdLikeKey(workerId, key.strip().toLowerCase()).stream()
							.map(TaskMapper::map)
							.distinct()
							.collect(Collectors.toUnmodifiableList())));
	}
	
	
	
}











