package tn.cita.app.service.v0.business.employee.manager.impl;

import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.exception.wrapper.EmployeeNotFoundException;
import tn.cita.app.mapper.EmployeeMapper;
import tn.cita.app.mapper.TaskMapper;
import tn.cita.app.model.dto.request.ClientPageRequest;
import tn.cita.app.model.dto.response.ManagerWorkerAssignmentResponse;
import tn.cita.app.repository.EmployeeRepository;
import tn.cita.app.repository.TaskRepository;
import tn.cita.app.service.v0.business.employee.manager.ManagerWorkerAssignmentService;
import tn.cita.app.util.ClientPageRequestUtils;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class ManagerWorkerAssignmentServiceImpl implements ManagerWorkerAssignmentService {
	
	private final EmployeeRepository employeeRepository;
	private final TaskRepository taskRepository;
	
	@Override
	public ManagerWorkerAssignmentResponse fetchAllWorkerTasks(final String username, final Integer workerId, 
			final ClientPageRequest clientPageRequest) {
		log.info("** Fetch all worker tasks by manager.. *\n");
		final var managerDto = this.employeeRepository.findByCredentialUsernameIgnoringCase(username)
				.map(EmployeeMapper::map)
				.orElseThrow(() -> new EmployeeNotFoundException("Employee with username: %s not found".formatted(username)));
		return new ManagerWorkerAssignmentResponse(managerDto, 
				this.taskRepository.findAllByWorkerId(workerId, 
						ClientPageRequestUtils.from(clientPageRequest))
					.map(TaskMapper::map));
	}
	
	@Override
	public ManagerWorkerAssignmentResponse searchAllLikeKey(final String username, final Integer workerId, final String key) {
		log.info("** Search all worker tasks like key by manager.. *\n");
		return new ManagerWorkerAssignmentResponse(
				this.employeeRepository.findByCredentialUsernameIgnoringCase(username)
						.map(EmployeeMapper::map)
						.orElseThrow(() -> new EmployeeNotFoundException(String
								.format("Employee with username: %s not found", username))), 
				new PageImpl<>(this.taskRepository
						.searchAllByWorkerIdLikeKey(workerId, key.strip().toLowerCase()).stream()
							.map(TaskMapper::map)
							.distinct()
							.toList()));
	}
	
	
	
}











