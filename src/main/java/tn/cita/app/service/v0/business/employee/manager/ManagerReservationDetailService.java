package tn.cita.app.service.v0.business.employee.manager;

import tn.cita.app.dto.request.ReservationAssignWorkerRequest;
import tn.cita.app.dto.response.ReservationBeginEndTask;
import tn.cita.app.dto.response.ReservationDetailResponse;
import tn.cita.app.dto.response.ReservationSubWorkerResponse;

public interface ManagerReservationDetailService {
	
	ReservationDetailResponse getReservationDetails(final Integer reservationId);
	ReservationBeginEndTask getBeginEndTask(final Integer reservationId);
	ReservationSubWorkerResponse getAllUnassignedSubWorkers(final String username, final Integer reservationId);
	ReservationSubWorkerResponse assignReservationWorkers(final String username, 
			final ReservationAssignWorkerRequest reservationAssignWorkerRequest);
	
}












