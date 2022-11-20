package tn.cita.app.service.v0.business.employee.worker.impl;

import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.exception.wrapper.EmployeeNotFoundException;
import tn.cita.app.exception.wrapper.ReservationNotFoundException;
import tn.cita.app.exception.wrapper.TaskAlreadyBeganException;
import tn.cita.app.exception.wrapper.TaskAlreadyEndedException;
import tn.cita.app.exception.wrapper.TaskNotBeganException;
import tn.cita.app.exception.wrapper.TaskNotFoundException;
import tn.cita.app.mapper.EmployeeMapper;
import tn.cita.app.mapper.TaskMapper;
import tn.cita.app.model.domain.ReservationStatus;
import tn.cita.app.model.domain.entity.Task;
import tn.cita.app.model.domain.id.TaskId;
import tn.cita.app.model.dto.TaskDto;
import tn.cita.app.model.dto.request.TaskBeginEndRequest;
import tn.cita.app.model.dto.request.TaskUpdateDescriptionRequest;
import tn.cita.app.repository.EmployeeRepository;
import tn.cita.app.repository.ReservationRepository;
import tn.cita.app.repository.TaskRepository;
import tn.cita.app.service.v0.business.employee.worker.WorkerReservationTaskService;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class WorkerReservationTaskServiceImpl implements WorkerReservationTaskService {
	
	private final EmployeeRepository employeeRepository;
	private final TaskRepository taskRepository;
	private final ReservationRepository reservationRepository;
	
	@Override
	public TaskDto fetchAssignedTask(final String username, final Integer reservationId) {
		log.info("** Fetch assigned task by worker.. *\n");
		return this.taskRepository.findById(new TaskId(this.employeeRepository
					.findByCredentialUsernameIgnoringCase(username)
					.map(EmployeeMapper::map)
					.orElseThrow(() -> new EmployeeNotFoundException(String
							.format("Employee with username: %s not found", username))).getId(), reservationId))
				.map(TaskMapper::map)
				.orElseThrow(() -> new TaskNotFoundException("Task not found"));
	}
	
	@Transactional
	@Override
	public TaskDto updateDescription(final TaskUpdateDescriptionRequest taskUpdateDescriptionRequest) {
		
		log.info("** Update description by worker.. *\n");
		
		final var worker = this.employeeRepository
				.findByCredentialUsernameIgnoringCase(taskUpdateDescriptionRequest.username())
				.map(EmployeeMapper::map)
				.orElseThrow(() -> new EmployeeNotFoundException(String
						.format("Employee with username: %s not found", taskUpdateDescriptionRequest.username())));
		final var reservation = this.reservationRepository
				.findById(taskUpdateDescriptionRequest.reservationId())
				.orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));
		final var task = this.taskRepository
				.findById(new TaskId(worker.getId(), reservation.getId()))
				.orElseThrow(() -> new TaskNotFoundException(String.format("Task not found")));
		
		Optional.ofNullable(task.getEndDate()).ifPresent(endDate -> {
			throw new TaskAlreadyEndedException("Task is already ended.\n"
					+ "Unfortunately, you cannot comment it anymore");
		});
		
		// update worker description..
		task.setWorkerDescription((taskUpdateDescriptionRequest.workerDescription() == null 
					|| taskUpdateDescriptionRequest.workerDescription().isBlank()) ? 
				null : taskUpdateDescriptionRequest.workerDescription().strip());
		return TaskMapper.map(this.taskRepository.save(task));
	}
	
	@Transactional
	@Override
	public TaskDto beginTask(final TaskBeginEndRequest taskBeginRequest) {
		
		log.info("** Begin task by worker.. *\n");
		
		final var worker = this.employeeRepository.findByCredentialUsernameIgnoringCase(taskBeginRequest.username())
				.map(EmployeeMapper::map)
				.orElseThrow(() -> new EmployeeNotFoundException(String
						.format("Employee with username: %s not found", taskBeginRequest.username())));
		final var reservation = this.reservationRepository
				.findById(taskBeginRequest.reservationId())
				.orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));
		final var task = this.taskRepository
				.findById(new TaskId(worker.getId(), reservation.getId()))
				.orElseThrow(() -> new TaskNotFoundException(String.format("Task not found")));
		
		Optional.ofNullable(task.getEndDate()).ifPresent(endDate -> {
			throw new TaskAlreadyEndedException("Task already ended");
		});
		Optional.ofNullable(task.getStartDate()).ifPresent(startDate -> {
			throw new TaskAlreadyBeganException("Task already began");
		});
		
		// begin task..
		task.setStartDate(LocalDateTime.now());
		task.setWorkerDescription((taskBeginRequest.workerDescription() == null 
					|| taskBeginRequest.workerDescription().isBlank()) ? 
				null : taskBeginRequest.workerDescription().strip());
		
		// make reservation as in_progress..
		if (!reservation.getStatus().equals(ReservationStatus.NOT_STARTED)
				&& !reservation.getStatus().equals(ReservationStatus.IN_PROGRESS))
			throw new IllegalArgumentException("Illegal reservation status when begining task");
		
		reservation.setStatus(ReservationStatus.IN_PROGRESS);
		
		// update reservation status..
		final var updatedReservation = this.reservationRepository.save(reservation);
		task.setReservationId(updatedReservation.getId());
		task.setReservation(updatedReservation);
		
		return TaskMapper.map(this.taskRepository.save(task));
	}
	
	@Transactional
	@Override
	public TaskDto endTask(final TaskBeginEndRequest taskEndRequest) {
		
		log.info("** End task by worker.. *\n");
		
		final var workerDto = this.employeeRepository
				.findByCredentialUsernameIgnoringCase(taskEndRequest.username())
				.map(EmployeeMapper::map)
				.orElseThrow(() -> new EmployeeNotFoundException(String
						.format("Employee with username: %s not found", taskEndRequest.username())));
		final var reservation = this.reservationRepository
				.findById(taskEndRequest.reservationId())
				.orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));
		final var task = this.taskRepository
				.findById(new TaskId(workerDto.getId(), reservation.getId()))
				.orElseThrow(() -> new TaskNotFoundException(String.format("Task not found")));
		
		if (task.getStartDate() == null)
			throw new TaskNotBeganException("Task have not began yet");
		
		Optional.ofNullable(task.getEndDate()).ifPresent(endDate -> {
			throw new TaskAlreadyEndedException("Task is already ended");
		});
		
		// update task to be ended..
		task.setEndDate(LocalDateTime.now());
		task.setWorkerDescription((taskEndRequest.workerDescription() == null 
					|| taskEndRequest.workerDescription().isBlank()) ? 
				null : taskEndRequest.workerDescription().strip());
		
		// fetch all assigned workers to this reservation..
		final var assignedOtherTaskDtos = this.taskRepository
				.findAllByReservationId(taskEndRequest.reservationId()).stream()
					.filter(t -> !t.getWorkerId().equals(workerDto.getId()))
					.distinct()
					.collect(Collectors.toUnmodifiableList());
		
		final boolean isAllTasksEnded = assignedOtherTaskDtos.stream()
					.map(Task::getEndDate)
					.allMatch(Objects::nonNull);
		
		// update reservation as COMPLETED if match..
		if (isAllTasksEnded) {
			reservation.setStatus(ReservationStatus.COMPLETED);
			reservation.setCompleteDate(LocalDateTime.now());
			final var completedReservation = this.reservationRepository.save(reservation);
			task.setReservationId(completedReservation.getId());
			task.setReservation(completedReservation);
		}
		
		return TaskMapper.map(this.taskRepository.save(task));
	}
	
	
	
}















