package tn.cita.app.service.v0.business.employee.manager;

import tn.cita.app.model.dto.ReservationDto;
import tn.cita.app.model.dto.request.ClientPageRequest;
import tn.cita.app.model.dto.request.ReservationAssignWorkerRequest;
import tn.cita.app.model.dto.response.ManagerReservationResponse;
import tn.cita.app.model.dto.response.ReservationSubWorkerResponse;

public interface ManagerReservationService {
	
	ManagerReservationResponse fetchAllReservations(final String username, final ClientPageRequest clientPageRequest);
	ReservationDto cancelReservation(final Integer reservationId);
	ManagerReservationResponse searchAllBySaloonIdLikeKey(final String username, final String key);
	ReservationSubWorkerResponse fetchAllUnassignedSubWorkers(final String username, final Integer reservationId);
	ReservationSubWorkerResponse assignReservationWorkers(final String username, 
			final ReservationAssignWorkerRequest reservationAssignWorkerRequest);
	
}












