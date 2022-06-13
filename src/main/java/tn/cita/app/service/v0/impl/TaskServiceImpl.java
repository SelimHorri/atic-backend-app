package tn.cita.app.service.v0.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.TaskDto;
import tn.cita.app.dto.request.ClientPageRequest;
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
	public Page<TaskDto> findAllByReservationId(final Integer reservationId) {
		return this.taskRepository.findAllByReservationId(reservationId, PageRequest.of(1 - 1, AppConstant.PAGE_SIZE))
				.map(TaskMapper::map);
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














