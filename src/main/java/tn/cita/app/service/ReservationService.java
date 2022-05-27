package tn.cita.app.service;

import org.springframework.data.domain.Page;

import tn.cita.app.dto.ReservationDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.request.ReservationDetailRequest;
import tn.cita.app.dto.response.ReservationContainerResponse;

public interface ReservationService {
	
	Page<ReservationDto> findAllByCustomerId(final Integer customerId, final ClientPageRequest clientPageRequest);
	ReservationDto findById(final Integer id);
	ReservationDto findByCode(final String code);
	ReservationContainerResponse getReservationDetails(final Integer reservationId);
	ReservationDto updateReservationDetails(final ReservationDetailRequest reservationDetailRequest);
	ReservationDto cancelReservation(final ReservationDto reservationDtoRequest);
	
}







