package tn.cita.app.service.v0;

import java.util.List;

import org.springframework.data.domain.Page;

import tn.cita.app.dto.TaskDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.repository.TaskRepository;

public interface TaskService {
	
	TaskRepository geTaskRepository();
	Page<TaskDto> findAllByReservationId(final Integer reservationId);
	List<TaskDto> findAllByWorkerId(final Integer workerId);
	Page<TaskDto> findAllByWorkerId(final Integer workerId, final ClientPageRequest clientPageRequest);
	
}













