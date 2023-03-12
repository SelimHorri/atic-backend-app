package tn.cita.app.service.v0.business.employee.worker;

import tn.cita.app.model.dto.response.ReservationDetailResponse;

public interface WorkerReservationDetailService {
	
	ReservationDetailResponse fetchReservationDetails(final Integer reservationId);
	
}






