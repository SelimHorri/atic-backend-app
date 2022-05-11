package tn.cita.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.TaskDto;
import tn.cita.app.mapper.TaskMapper;
import tn.cita.app.repository.TaskRepository;
import tn.cita.app.service.TaskService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
	
	private final TaskRepository taskRepository;
	
	@Override
	public List<TaskDto> findAllByReservationId(final Integer reservationId) {
		return this.taskRepository.findAllByReservationId(reservationId)
				.stream()
					.map(TaskMapper::map)
					.distinct()
					.collect(Collectors.toUnmodifiableList());
	}
	
	
	
}














