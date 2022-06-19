package tn.cita.app.service.v0.business.employee.worker;

import tn.cita.app.dto.TaskDto;
import tn.cita.app.dto.request.TaskBeginEndRequest;
import tn.cita.app.dto.request.TaskUpdateDescriptionRequest;
import tn.cita.app.dto.response.ReservationDetailResponse;

public interface WorkerReservationDetailService {
	
	ReservationDetailResponse getReservationDetails(final Integer reservationId);
	TaskDto getAssignedTask(final String username, final Integer reservationId);
	TaskDto updateDescription(final TaskUpdateDescriptionRequest taskUpdateDescriptionRequest);
	TaskDto beginTask(final TaskBeginEndRequest taskBeginRequest);
	TaskDto endTask(final TaskBeginEndRequest taskEndRequest);
	
}











