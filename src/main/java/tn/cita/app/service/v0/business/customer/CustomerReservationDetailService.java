package tn.cita.app.service.v0.business.customer;

import tn.cita.app.dto.ReservationDto;
import tn.cita.app.dto.request.ReservationDetailRequest;
import tn.cita.app.dto.response.ReservationDetailResponse;

public interface CustomerReservationDetailService {
	
	ReservationDetailResponse fetchReservationDetails(final Integer reservationId);
	ReservationDto updateReservationDetails(final ReservationDetailRequest reservationDetailRequest);
	
}












