package tn.cita.app.service.v0.business.employee.worker;

import tn.cita.app.model.dto.TaskDto;
import tn.cita.app.model.dto.request.TaskBeginEndRequest;
import tn.cita.app.model.dto.request.TaskUpdateDescriptionRequest;

public interface WorkerReservationTaskService {
	
	TaskDto fetchAssignedTask(final String username, final Integer reservationId);
	TaskDto updateDescription(final TaskUpdateDescriptionRequest taskUpdateDescriptionRequest);
	TaskDto beginTask(final TaskBeginEndRequest taskBeginRequest);
	TaskDto endTask(final TaskBeginEndRequest taskEndRequest);
	
}











