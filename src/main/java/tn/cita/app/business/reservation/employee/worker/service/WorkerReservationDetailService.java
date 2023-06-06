package tn.cita.app.business.reservation.employee.worker.service;

import tn.cita.app.model.dto.response.ReservationDetailResponse;

public interface WorkerReservationDetailService {
	
	ReservationDetailResponse fetchReservationDetails(final Integer reservationId);
	
}



