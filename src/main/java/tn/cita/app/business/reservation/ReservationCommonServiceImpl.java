package tn.cita.app.business.reservation;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tn.cita.app.business.reservation.employee.manager.model.ReservationAssignWorkerRequest;
import tn.cita.app.business.reservation.employee.manager.model.ReservationSubWorkerResponse;
import tn.cita.app.exception.wrapper.*;
import tn.cita.app.mapper.EmployeeMapper;
import tn.cita.app.mapper.ReservationMapper;
import tn.cita.app.mapper.TaskMapper;
import tn.cita.app.model.domain.ReservationStatus;
import tn.cita.app.model.domain.entity.Task;
import tn.cita.app.model.domain.id.TaskId;
import tn.cita.app.model.dto.ReservationDto;
import tn.cita.app.model.dto.TaskDto;
import tn.cita.app.repository.EmployeeRepository;
import tn.cita.app.repository.ReservationRepository;
import tn.cita.app.repository.TaskRepository;
import tn.cita.app.util.StringWrapperUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class ReservationCommonServiceImpl implements ReservationCommonService {
	
	private final EmployeeRepository employeeRepository;
	private final ReservationRepository reservationRepository;
	private final TaskRepository taskRepository;
	
	@Transactional
	@Override
	public ReservationDto cancelReservation(final Integer reservationId) {
		
		log.info("** Cancelling reservation.. *");
		
		final var reservation = this.reservationRepository.findById(reservationId)
				.orElseThrow(ReservationNotFoundException::new);
		
		// TODO: check if reservation has been completed, should not be cancelled (or changed) anymore
		// Add completeDate of reservation along with COMPLETED status to this check
		switch (reservation.getStatus()) {
			case COMPLETED -> throw new ReservationAlreadyCompletedException("Reservation is already completed");
			case CANCELLED -> throw new ReservationAlreadyCancelledException("Reservation is already cancelled");
			case OUTDATED -> throw new ReservationAlreadyOutdatedException("Reservation is already outdated");
			case NOT_CLOSED -> throw new ReservationAlreadyNotClosedException("Reservation is already not-closed");
			case NOT_STARTED, IN_PROGRESS -> {
				reservation.setCancelDate(LocalDateTime.now());
				reservation.setStatus(ReservationStatus.CANCELLED);
			}
		};
		
		return ReservationMapper.toDto(this.reservationRepository.save(reservation));
	}
	
	@Override
	public ReservationSubWorkerResponse fetchAllUnassignedSubWorkers(final String username, final Integer reservationId) {
		log.info("** Fetch all unassigned sub workers.. *");
		
		final var managerDto = this.employeeRepository
				.findByCredentialUsernameIgnoringCase(username)
				.map(EmployeeMapper::toDto)
				.orElseThrow(() -> new EmployeeNotFoundException(
						"Employee with username: %s not found".formatted(username)));
		
		final var assignedWorkersIds = this.taskRepository
				.findAllByReservationId(reservationId).stream()
					.map(TaskMapper::toDto)
					.map(TaskDto::getWorkerId)
					.distinct()
					.toList();
		
		final var unassignedWorkerDtos = this.employeeRepository
				.findAllByManagerId(managerDto.getId()).stream()
					.map(EmployeeMapper::toDto)
					.filter(w -> !assignedWorkersIds.contains(w.getId()))
					.toList();
		
		return new ReservationSubWorkerResponse(
				this.reservationRepository.findById(reservationId)
						.map(ReservationMapper::toDto)
						.orElseThrow(ReservationNotFoundException::new), 
				new PageImpl<>(unassignedWorkerDtos));
	}
	
	@Transactional
	@Override
	public ReservationSubWorkerResponse assignReservationWorkers(final String username, 
			final ReservationAssignWorkerRequest reservationAssignWorkerRequest) {
		log.info("** Assign workers to a reservation.. *");
		
		final var reservation = this.reservationRepository.findById(reservationAssignWorkerRequest.reservationId())
				.orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));
		final boolean isAlreadyAssigned = reservationAssignWorkerRequest.assignedWorkersIds().stream()
						.map(workerId -> new TaskId(workerId, reservation.getId()))
						.anyMatch(this.taskRepository::existsById);
		if (isAlreadyAssigned)
			throw new TaskAlreadyAssignedException("Worker is already assigned");
		
		final List<Task> assignedWorkers = new ArrayList<>();
		final var task = new Task();
		task.setReservationId(reservation.getId());
		/*
		task.setReservation(reservation);
		*/
		task.setManagerDescription(StringWrapperUtils
				.trimIfBlank(reservationAssignWorkerRequest.managerDescription()));
		
		for (int workerId: reservationAssignWorkerRequest.assignedWorkersIds()) {
			task.setTaskDate(LocalDateTime.now()); // added! cause object is persisted natively..
			task.setIdentifier(UUID.randomUUID().toString());
			task.setWorkerId(workerId);
			/*
			task.setWorker(this.employeeRepository.findById(workerId)
					.orElseThrow(EmployeeNotFoundException::new));
			*/
			this.taskRepository.saveTask(task);
			assignedWorkers.add(this.taskRepository
					.findById(new TaskId(task.getWorkerId(), task.getReservationId()))
					.orElseThrow(TaskNotFoundException::new));
		}
		
		final var savedAssignedWorkers = assignedWorkers.stream()
				.map(Task::getWorkerId)
				.map(workerId -> this.employeeRepository.findById(workerId)
						.map(EmployeeMapper::toDto)
						.orElseThrow(EmployeeNotFoundException::new))
				.toList();
		
		return new ReservationSubWorkerResponse(ReservationMapper.toDto(reservation), new PageImpl<>(savedAssignedWorkers));
	}
	
}



