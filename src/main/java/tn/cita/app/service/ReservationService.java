package tn.cita.app.service;

import java.util.List;

import tn.cita.app.dto.ReservationDto;

public interface ReservationService {
	
	List<ReservationDto> findAllByCustomerId(final Integer customerId);
	
}







