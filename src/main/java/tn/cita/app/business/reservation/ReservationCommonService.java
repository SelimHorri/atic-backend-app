package tn.cita.app.business.reservation;

import tn.cita.app.business.reservation.employee.manager.model.ReservationAssignWorkerRequest;
import tn.cita.app.business.reservation.employee.manager.model.ReservationSubWorkerResponse;
import tn.cita.app.model.dto.ReservationDto;

public interface ReservationCommonService {
	
	ReservationDto cancelReservation(final Integer reservationId);
	ReservationSubWorkerResponse fetchAllUnassignedSubWorkers(final String username, final Integer reservationId);
	ReservationSubWorkerResponse assignReservationWorkers(final String username, 
			final ReservationAssignWorkerRequest reservationAssignWorkerRequest);
	
}



