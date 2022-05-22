package tn.cita.app.service;

import java.util.List;

import tn.cita.app.dto.ReservationDto;
import tn.cita.app.dto.request.ReservationDetailRequest;
import tn.cita.app.dto.response.ReservationContainerResponse;

public interface ReservationService {
	
	List<ReservationDto> findAllByCustomerId(final Integer customerId);
	ReservationDto findById(final Integer id);
	ReservationDto findByCode(final String code);
	ReservationContainerResponse getReservationDetails(final Integer reservationId);
	ReservationDto updateReservationDetails(final ReservationDetailRequest reservationDetailRequest);
	ReservationDto cancelReservation(final ReservationDto reservationDtoRequest);
	
}







