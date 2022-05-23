package tn.cita.app.service;

import org.springframework.data.domain.Page;

import tn.cita.app.dto.TaskDto;

public interface TaskService {
	
	Page<TaskDto> findAllByReservationId(final Integer reservationId);
	
}













