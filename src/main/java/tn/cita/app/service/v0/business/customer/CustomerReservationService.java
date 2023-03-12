package tn.cita.app.service.v0.business.customer;

import tn.cita.app.model.dto.ReservationDto;
import tn.cita.app.model.dto.request.ClientPageRequest;
import tn.cita.app.model.dto.request.ReservationRequest;
import tn.cita.app.model.dto.response.CustomerReservationResponse;

public interface CustomerReservationService {
	
	CustomerReservationResponse fetchAllReservations(final String username, final ClientPageRequest clientPageRequest);
	ReservationDto cancelReservation(final Integer reservationId);
	ReservationDto createReservation(final ReservationRequest reservationRequest);
	CustomerReservationResponse searchAllByCustomerIdLikeKey(final String username, final String key);
	
}






