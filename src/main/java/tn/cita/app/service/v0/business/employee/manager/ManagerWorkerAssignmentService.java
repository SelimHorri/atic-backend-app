package tn.cita.app.service.v0.business.employee.manager;

import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.response.ManagerWorkerAssignmentResponse;

public interface ManagerWorkerAssignmentService {
	
	ManagerWorkerAssignmentResponse getAllWorkerTasks(final String username, 
			final Integer workerId, final ClientPageRequest clientPageRequest);
	
}









