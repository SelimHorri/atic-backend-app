package tn.cita.app.service;

import java.util.List;

import tn.cita.app.dto.TaskDto;

public interface TaskService {
	
	List<TaskDto> findAllByReservationId(final Integer reservationId);
	
}













