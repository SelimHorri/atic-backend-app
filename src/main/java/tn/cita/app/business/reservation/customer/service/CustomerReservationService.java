package tn.cita.app.business.reservation.customer.service;

import tn.cita.app.business.reservation.customer.model.CustomerReservationResponse;
import tn.cita.app.model.dto.ReservationDto;
import tn.cita.app.model.dto.request.ClientPageRequest;
import tn.cita.app.model.dto.request.ReservationRequest;

public interface CustomerReservationService {
	
	CustomerReservationResponse fetchAllReservations(final String username, final ClientPageRequest clientPageRequest);
	ReservationDto cancelReservation(final Integer reservationId);
	ReservationDto createReservation(final ReservationRequest reservationRequest);
	CustomerReservationResponse searchAllByCustomerIdLikeKey(final String username, final String key);
	
}



