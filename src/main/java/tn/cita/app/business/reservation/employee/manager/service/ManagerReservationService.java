package tn.cita.app.business.reservation.employee.manager.service;

import tn.cita.app.business.reservation.employee.manager.model.ManagerReservationResponse;
import tn.cita.app.business.reservation.employee.manager.model.ReservationAssignWorkerRequest;
import tn.cita.app.business.reservation.employee.manager.model.ReservationSubWorkerResponse;
import tn.cita.app.model.dto.ReservationDto;
import tn.cita.app.model.dto.request.ClientPageRequest;

public interface ManagerReservationService {
	
	ManagerReservationResponse fetchAllReservations(final String username);
	ManagerReservationResponse fetchAllReservations(final String username, final ClientPageRequest clientPageRequest);
	ReservationDto cancelReservation(final Integer reservationId);
	ManagerReservationResponse searchAllBySaloonIdLikeKey(final String username, final String key);
	ReservationSubWorkerResponse fetchAllUnassignedSubWorkers(final String username, final Integer reservationId);
	ReservationSubWorkerResponse assignReservationWorkers(final String username, 
			final ReservationAssignWorkerRequest reservationAssignWorkerRequest);
	
}



