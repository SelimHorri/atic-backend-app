package tn.cita.app.service.v0.common.impl;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.domain.ReservationStatus;
import tn.cita.app.domain.entity.Task;
import tn.cita.app.domain.id.TaskId;
import tn.cita.app.dto.ReservationDto;
import tn.cita.app.dto.TaskDto;
import tn.cita.app.dto.request.ReservationAssignWorkerRequest;
import tn.cita.app.dto.response.ReservationSubWorkerResponse;
import tn.cita.app.exception.wrapper.EmployeeNotFoundException;
import tn.cita.app.exception.wrapper.ReservationAlreadyCancelledException;
import tn.cita.app.exception.wrapper.ReservationAlreadyCompletedException;
import tn.cita.app.exception.wrapper.ReservationAlreadyNotClosedException;
import tn.cita.app.exception.wrapper.ReservationAlreadyOutdatedException;
import tn.cita.app.exception.wrapper.ReservationNotFoundException;
import tn.cita.app.exception.wrapper.TaskAlreadyAssigned;
import tn.cita.app.exception.wrapper.TaskNotFoundException;
import tn.cita.app.mapper.ReservationMapper;
import tn.cita.app.service.v0.EmployeeService;
import tn.cita.app.service.v0.ReservationService;
import tn.cita.app.service.v0.TaskService;
import tn.cita.app.service.v0.common.ReservationCommonService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationCommonServiceImpl implements ReservationCommonService {
	
	private final EmployeeService employeeService;
	private final ReservationService reservationService;
	private final TaskService taskService;
	
	@Transactional
	@Override
	public ReservationDto cancelReservation(final Integer reservationId) {

		final var reservation = this.reservationService.getReservationRepository().findById(reservationId)
				.orElseThrow(() -> new ReservationNotFoundException(String
						.format("Reservation with id: %s not found", reservationId)));
		
		// TODO: check if reservation has been completed, should not be cancelled (or changed) anymore
		// Add completeDate of reservation along with COMPLETED status to this check
		if (reservation.getStatus().equals(ReservationStatus.COMPLETED))
			throw new ReservationAlreadyCompletedException("Reservation is already completed");
		else if (reservation.getStatus().equals(ReservationStatus.CANCELLED))
			throw new ReservationAlreadyCancelledException("Reservation is already cancelled");
		else if (reservation.getStatus().equals(ReservationStatus.OUTDATED))
			throw new ReservationAlreadyOutdatedException("Reservation is already outdated");
		else if (reservation.getStatus().equals(ReservationStatus.NOT_CLOSED))
			throw new ReservationAlreadyNotClosedException("Reservation is already not-closed");
		
		// update
		reservation.setCancelDate(LocalDateTime.now());
		reservation.setStatus(ReservationStatus.CANCELLED);
		
		return ReservationMapper.map(this.reservationService.getReservationRepository().save(reservation));
	}
	
	@Override
	public ReservationSubWorkerResponse getAllUnassignedSubWorkers(final String username, final Integer reservationId) {

		final var managerDto = this.employeeService.findByCredentialUsername(username);
		final var assignedWorkersIds = this.taskService
				.findAllByReservationId(reservationId).stream()
					.map(TaskDto::getWorkerId)
					.distinct()
					.collect(Collectors.toUnmodifiableSet());
		final var unassignedWorkerDtos = this.employeeService
				.findAllByManagerId(managerDto.getId()).stream()
					.filter(w -> !assignedWorkersIds.contains(w.getId()))
					.distinct()
					.collect(Collectors.toUnmodifiableList());
		
		return new ReservationSubWorkerResponse(this.reservationService.findById(reservationId), new PageImpl<>(unassignedWorkerDtos));
	}
	
	@Transactional
	@Override
	public ReservationSubWorkerResponse assignReservationWorkers(final String username, 
			final ReservationAssignWorkerRequest reservationAssignWorkerRequest) {
		
		final var reservation = this.reservationService.getReservationRepository()
				.findById(reservationAssignWorkerRequest.getReservationId())
					.orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));
		final boolean isAlreadyAssigned = reservationAssignWorkerRequest.getAssignedWorkersIds().stream()
						.map(workerId -> new TaskId(workerId, reservation.getId()))
						.anyMatch(this.taskService.geTaskRepository()::existsById);
		if (isAlreadyAssigned)
			throw new TaskAlreadyAssigned("Worker is already assigned");
		
		final List<Task> assignedWorkers = new ArrayList<>();
		final var task = new Task();
		task.setReservationId(reservation.getId());
		task.setReservation(reservation);
		task.setManagerDescription(reservationAssignWorkerRequest.getManagerDescription());
		reservationAssignWorkerRequest.getAssignedWorkersIds().forEach(workerId -> {
			task.setWorkerId(workerId);
			task.setWorker(this.employeeService.getEmployeeRepository()
					.findById(workerId)
					.orElseThrow(EmployeeNotFoundException::new));
			this.taskService.geTaskRepository().saveTask(task);
			assignedWorkers.add(this.taskService.geTaskRepository()
					.findById(new TaskId(task.getWorkerId(), task.getReservationId()))
					.orElseThrow(TaskNotFoundException::new));
		});
		
		final var savedAssignedWorkers = assignedWorkers.stream()
				.map(Task::getWorkerId)
				.map(this.employeeService::findById)
				.distinct()
				.collect(Collectors.toUnmodifiableList());
		
		return new ReservationSubWorkerResponse(ReservationMapper.map(reservation), new PageImpl<>(savedAssignedWorkers));
	}
	
	
	
}













