package tn.cita.app.business.reservation.employee.manager.service;

import tn.cita.app.business.reservation.employee.manager.model.ManagerWorkerAssignmentResponse;
import tn.cita.app.model.dto.request.ClientPageRequest;

public interface ManagerWorkerAssignmentService {
	
	ManagerWorkerAssignmentResponse fetchAllWorkerTasks(final String username, 
			final Integer workerId, final ClientPageRequest clientPageRequest);
	ManagerWorkerAssignmentResponse searchAllLikeKey(final String username, final Integer workerId, final String key);
	
}



