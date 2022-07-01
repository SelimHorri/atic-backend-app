package tn.cita.app.service.v0.business.employee.manager;

import tn.cita.app.dto.ReservationDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.response.ManagerReservationResponse;
import tn.cita.app.dto.response.ReservationSubWorkerResponse;

public interface ManagerReservationService {
	
	ManagerReservationResponse getAllReservations(final String username, final ClientPageRequest clientPageRequest);
	ReservationDto cancelReservation(final Integer reservationId);
	ManagerReservationResponse searchAllBySaloonIdLikeKey(final String username, final String key);
	ReservationSubWorkerResponse getAllUnassignedSubWorkers(final String username, final Integer reservationId);
	
}












