package tn.cita.app.business.reservation.customer.service;

import tn.cita.app.model.dto.ReservationDto;
import tn.cita.app.model.dto.request.ReservationDetailRequest;
import tn.cita.app.model.dto.response.ReservationDetailResponse;

public interface CustomerReservationDetailService {
	
	ReservationDetailResponse fetchReservationDetails(final Integer reservationId);
	ReservationDetailResponse fetchReservationDetails(final String reservationIdentifier);
	ReservationDto updateReservationDetails(final ReservationDetailRequest reservationDetailRequest);
	
}



