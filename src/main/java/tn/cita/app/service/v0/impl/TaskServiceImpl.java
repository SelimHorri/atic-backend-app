package tn.cita.app.service.v0.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.domain.id.TaskId;
import tn.cita.app.dto.TaskDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.exception.wrapper.TaskNotFoundException;
import tn.cita.app.mapper.TaskMapper;
import tn.cita.app.repository.TaskRepository;
import tn.cita.app.service.v0.TaskService;
import tn.cita.app.util.ClientPageRequestUtils;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
	
	private final TaskRepository taskRepository;
	
	@Override
	public TaskDto findById(final TaskId taskId) {
		log.info("** Find task by id.. *\n");
		return this.taskRepository.findById(taskId)
				.map(TaskMapper::map)
				.orElseThrow(() -> new TaskNotFoundException(String.format("Task not found")));
	}
	
	@Override
	public List<TaskDto> findAllByReservationId(final Integer reservationId) {
		log.info("** Find all tasks by reservationId.. *\n");
		return this.taskRepository.findAllByReservationId(reservationId).stream()
				.map(TaskMapper::map)
				.distinct()
				.collect(Collectors.toUnmodifiableList());
	}
	
	@Override
	public List<TaskDto> findAllByWorkerId(final Integer workerId) {
		log.info("** Find all tasls by workerId.. *\n");
		return this.taskRepository.findAllByWorkerId(workerId).stream()
				.map(TaskMapper::map)
				.distinct()
				.collect(Collectors.toUnmodifiableList());
	}
	
	@Override
	public Page<TaskDto> findAllByWorkerId(final Integer workerId, final ClientPageRequest clientPageRequest) {
		log.info("** Find all paged tasks by workerId.. *\n");
		return this.taskRepository.findAllByWorkerId(workerId, 
					ClientPageRequestUtils.from(clientPageRequest))
				.map(TaskMapper::map);
	}
	
	
	
}














