package tn.cita.app.service.v0;

import java.util.List;

import org.springframework.data.domain.Page;

import tn.cita.app.model.domain.id.TaskId;
import tn.cita.app.model.dto.TaskDto;
import tn.cita.app.model.dto.request.ClientPageRequest;

public interface TaskService {
	
	TaskDto findById(final TaskId taskId);
	TaskDto findByIdentifier(final String identifier);
	List<TaskDto> findAllByReservationId(final Integer reservationId);
	List<TaskDto> findAllByWorkerId(final Integer workerId);
	Page<TaskDto> findAllByWorkerId(final Integer workerId, final ClientPageRequest clientPageRequest);
	
}







