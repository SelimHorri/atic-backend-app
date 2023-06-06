package tn.cita.app.business.reservation.employee.manager.service;

import tn.cita.app.business.reservation.employee.manager.model.ReservationAssignWorkerRequest;
import tn.cita.app.business.reservation.employee.manager.model.ReservationSubWorkerResponse;
import tn.cita.app.model.dto.response.ReservationBeginEndTask;
import tn.cita.app.model.dto.response.ReservationDetailResponse;

public interface ManagerReservationDetailService {
	
	ReservationDetailResponse fetchReservationDetails(final Integer reservationId);
	ReservationBeginEndTask fetchBeginEndTask(final Integer reservationId);
	ReservationSubWorkerResponse fetchAllUnassignedSubWorkers(final String username, final Integer reservationId);
	ReservationSubWorkerResponse assignReservationWorkers(final String username, 
			final ReservationAssignWorkerRequest reservationAssignWorkerRequest);
	
}



