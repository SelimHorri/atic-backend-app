package tn.cita.app.service.v0.business.customer;

import tn.cita.app.dto.ReservationDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.response.CustomerReservationResponse;

public interface CustomerReservationService {
	
	CustomerReservationResponse getReservationsByUsername(final String username, final ClientPageRequest clientPageRequest);
	ReservationDto cancelReservation(final ReservationDto reservationDtoRequest);
	
}








