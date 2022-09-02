package tn.cita.app.service.v0.common;

import tn.cita.app.dto.ReservationDto;
import tn.cita.app.dto.request.ReservationAssignWorkerRequest;
import tn.cita.app.dto.response.ReservationSubWorkerResponse;

public interface ReservationCommonService {
	
	ReservationDto cancelReservation(final Integer reservationId);
	ReservationSubWorkerResponse fetchAllUnassignedSubWorkers(final String username, final Integer reservationId);
	ReservationSubWorkerResponse assignReservationWorkers(final String username, 
			final ReservationAssignWorkerRequest reservationAssignWorkerRequest);
	
}











