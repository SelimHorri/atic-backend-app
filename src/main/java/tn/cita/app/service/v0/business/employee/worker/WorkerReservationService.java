package tn.cita.app.service.v0.business.employee.worker;

import java.util.List;

import org.springframework.data.domain.Page;

import tn.cita.app.dto.TaskDto;
import tn.cita.app.dto.request.ClientPageRequest;

public interface WorkerReservationService {
	
	List<TaskDto> getAllReservations(final String username);
	Page<TaskDto> getAllReservations(final String username, final ClientPageRequest clientPageRequest);
	
}








