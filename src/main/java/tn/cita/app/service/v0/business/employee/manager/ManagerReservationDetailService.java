package tn.cita.app.service.v0.business.employee.manager;

import tn.cita.app.model.dto.request.ReservationAssignWorkerRequest;
import tn.cita.app.model.dto.response.ReservationBeginEndTask;
import tn.cita.app.model.dto.response.ReservationDetailResponse;
import tn.cita.app.model.dto.response.ReservationSubWorkerResponse;

public interface ManagerReservationDetailService {
	
	ReservationDetailResponse fetchReservationDetails(final Integer reservationId);
	ReservationBeginEndTask fetchBeginEndTask(final Integer reservationId);
	ReservationSubWorkerResponse fetchAllUnassignedSubWorkers(final String username, final Integer reservationId);
	ReservationSubWorkerResponse assignReservationWorkers(final String username, 
			final ReservationAssignWorkerRequest reservationAssignWorkerRequest);
	
}












