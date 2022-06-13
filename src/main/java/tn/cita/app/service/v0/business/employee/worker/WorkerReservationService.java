package tn.cita.app.service.v0.business.employee.worker;

import org.springframework.data.domain.Page;

import tn.cita.app.dto.TaskDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.response.ReservationDetailResponse;

public interface WorkerReservationService {
	
	Page<TaskDto> getAllReservations(final String username, final ClientPageRequest clientPageRequest);
	Page<TaskDto> getAllReservations(final String username);
	ReservationDetailResponse getReservationDetails(final Integer reservationId);
	
}








