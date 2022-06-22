package tn.cita.app.service.v0.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.domain.id.TaskId;
import tn.cita.app.dto.TaskDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.exception.wrapper.TaskNotFoundException;
import tn.cita.app.mapper.TaskMapper;
import tn.cita.app.repository.TaskRepository;
import tn.cita.app.service.v0.TaskService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {
	
	private final TaskRepository taskRepository;
	
	@Override
	public TaskRepository geTaskRepository() {
		return this.taskRepository;
	}
	
	@Override
	public TaskDto findById(final TaskId taskId) {
		return this.taskRepository.findById(taskId)
				.map(TaskMapper::map)
				.orElseThrow(() -> new TaskNotFoundException(String.format("Task not found")));
	}
	
	@Override
	public List<TaskDto> findAllByReservationId(final Integer reservationId) {
		return this.taskRepository.findAllByReservationId(reservationId)
				.stream()
					.map(TaskMapper::map)
					.distinct()
					.collect(Collectors.toUnmodifiableList());
	}
	
	@Override
	public List<TaskDto> findAllByWorkerId(final Integer workerId) {
		return this.taskRepository.findAllByWorkerId(workerId)
				.stream()
					.map(TaskMapper::map)
					.distinct()
					.collect(Collectors.toUnmodifiableList());
	}
	
	@Override
	public Page<TaskDto> findAllByWorkerId(final Integer workerId, final ClientPageRequest clientPageRequest) {
		return this.taskRepository.findAllByWorkerId(workerId, PageRequest.of(clientPageRequest.getOffset() - 1, 
					clientPageRequest.getSize(), 
					clientPageRequest.getSortDirection(), 
					clientPageRequest.getSortBy()))
				.map(TaskMapper::map);
	}
	
	
	
}














