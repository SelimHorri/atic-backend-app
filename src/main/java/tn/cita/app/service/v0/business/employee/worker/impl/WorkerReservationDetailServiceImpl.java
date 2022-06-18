package tn.cita.app.service.v0.business.employee.worker.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.domain.ReservationStatus;
import tn.cita.app.domain.id.TaskId;
import tn.cita.app.dto.TaskDto;
import tn.cita.app.dto.request.TaskBeginRequest;
import tn.cita.app.dto.request.TaskUpdateDescriptionRequest;
import tn.cita.app.dto.response.ReservationDetailResponse;
import tn.cita.app.exception.wrapper.ReservationNotFoundException;
import tn.cita.app.exception.wrapper.TaskAlreadyBegan;
import tn.cita.app.exception.wrapper.TaskNotFoundException;
import tn.cita.app.mapper.TaskMapper;
import tn.cita.app.service.v0.EmployeeService;
import tn.cita.app.service.v0.OrderedDetailService;
import tn.cita.app.service.v0.ReservationService;
import tn.cita.app.service.v0.TaskService;
import tn.cita.app.service.v0.business.employee.worker.WorkerReservationDetailService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WorkerReservationDetailServiceImpl implements WorkerReservationDetailService {
	
	private final EmployeeService employeeService;
	private final TaskService taskService;
	private final ReservationService reservationService;
	private final OrderedDetailService orderedDetailService;
	
	@Override
	public ReservationDetailResponse getReservationDetails(final Integer reservationId) {
		final var reservationDto = this.reservationService.findById(reservationId);
		return ReservationDetailResponse.builder()
				.reservationDto(reservationDto)
				.orderedDetailDtos(this.orderedDetailService.findAllByReservationId(reservationDto.getId()))
				.taskDtos(this.taskService.findAllByReservationId(reservationDto.getId()))
				.build();
	}
	
	@Override
	public TaskDto getAssignedTask(final String username, final Integer reservationId) {
		return this.taskService.findById(new TaskId(this.employeeService.findByUsername(username).getId(), reservationId));
	}
	
	@Transactional
	@Override
	public TaskDto updateDescription(final TaskUpdateDescriptionRequest taskUpdateDescriptionRequest) {
		
		final var worker = this.employeeService.findByUsername(taskUpdateDescriptionRequest.getUsername());
		final var reservation = this.reservationService.getReservationRepository()
				.findById(taskUpdateDescriptionRequest.getReservationId())
					.orElseThrow(() -> new ReservationNotFoundException(String
							.format("Reservation with id: %s not found", taskUpdateDescriptionRequest.getReservationId())));
		final var task = this.taskService.geTaskRepository()
				.findById(new TaskId(worker.getId(), reservation.getId()))
					.orElseThrow(() -> new TaskNotFoundException(String.format("Task not found")));
		
		// update worker description..
		task.setWorkerDescription(taskUpdateDescriptionRequest.getWorkerDescription());
		return TaskMapper.map(this.taskService.geTaskRepository().save(task));
	}
	
	@Transactional
	@Override
	public TaskDto beginTask(final TaskBeginRequest taskBeginRequest) {
		
		final var worker = this.employeeService.findByUsername(taskBeginRequest.getUsername());
		final var reservation = this.reservationService.getReservationRepository()
				.findById(taskBeginRequest.getReservationId())
					.orElseThrow(() -> new ReservationNotFoundException(String
							.format("Reservation with id: %s not found", taskBeginRequest.getReservationId())));
		final var task = this.taskService.geTaskRepository()
				.findById(new TaskId(worker.getId(), reservation.getId()))
					.orElseThrow(() -> new TaskNotFoundException(String.format("Task not found")));
		
		Optional.ofNullable(task.getStartDate()).ifPresent(startDate -> {
			throw new TaskAlreadyBegan("Task already started");
		});
		
		// begin task..
		task.setStartDate(LocalDateTime.now());
		task.setWorkerDescription(taskBeginRequest.getWorkerDescription());
		
		// make reservation as in_progress..
		if (!reservation.getStatus().equals(ReservationStatus.NOT_STARTED)
				&& !reservation.getStatus().equals(ReservationStatus.IN_PROGRESS))
			throw new IllegalArgumentException("Illegal reservation status when begining task");
		
		reservation.setStatus(ReservationStatus.IN_PROGRESS);
		
		// update reservation status..
		final var updatedReservation = this.reservationService.getReservationRepository().save(reservation);
		task.setReservationId(updatedReservation.getId());
		task.setReservation(updatedReservation);
		
		return TaskMapper.map(this.taskService.geTaskRepository().save(task));
	}
	
	
	
}














