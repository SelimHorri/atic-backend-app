package tn.cita.app.service.v0.business.employee.worker;

import org.springframework.data.domain.Page;

import tn.cita.app.dto.TaskDto;
import tn.cita.app.dto.request.ClientPageRequest;

public interface WorkerReservationService {
	
	Page<TaskDto> getAllReservations(final String username, final ClientPageRequest clientPageRequest);
	Page<TaskDto> getAllReservations(final String username);
	Page<TaskDto> searchAllLikeKey(final String username, final String key);
	
}








