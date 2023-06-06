package tn.cita.app.business.reservation.employee.worker.service;

import tn.cita.app.business.reservation.employee.worker.model.TaskBeginEndRequest;
import tn.cita.app.business.reservation.employee.worker.model.TaskUpdateDescriptionRequest;
import tn.cita.app.model.dto.TaskDto;

public interface WorkerReservationTaskService {
	
	TaskDto fetchAssignedTask(final String username, final Integer reservationId);
	TaskDto updateDescription(final TaskUpdateDescriptionRequest taskUpdateDescriptionRequest);
	TaskDto beginTask(final TaskBeginEndRequest taskBeginRequest);
	TaskDto endTask(final TaskBeginEndRequest taskEndRequest);
	
}



