package tn.cita.app.business.reservation.employee.worker.service;

import org.springframework.data.domain.Page;
import tn.cita.app.model.dto.TaskDto;
import tn.cita.app.model.dto.request.ClientPageRequest;

public interface WorkerReservationService {
	
	Page<TaskDto> fetchAllReservations(final String username, final ClientPageRequest clientPageRequest);
	Page<TaskDto> fetchAllReservations(final String username);
	Page<TaskDto> searchAllLikeKey(final String username, final String key);
	
}



