package tn.cita.app.service.v0.business.employee.manager.impl;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.domain.ReservationStatus;
import tn.cita.app.dto.ReservationDto;
import tn.cita.app.dto.TaskDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.response.ManagerReservationResponse;
import tn.cita.app.dto.response.ReservationSubWorkerResponse;
import tn.cita.app.exception.wrapper.ReservationAlreadyCompletedException;
import tn.cita.app.exception.wrapper.ReservationNotFoundException;
import tn.cita.app.mapper.ReservationMapper;
import tn.cita.app.service.v0.EmployeeService;
import tn.cita.app.service.v0.ReservationService;
import tn.cita.app.service.v0.TaskService;
import tn.cita.app.service.v0.business.employee.manager.ManagerReservationService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ManagerReservationServiceImpl implements ManagerReservationService {
	
	private final EmployeeService employeeService;
	private final ReservationService reservationService;
	private final TaskService taskService;
	
	@Override
	public ManagerReservationResponse getAllReservations(final String username, final ClientPageRequest clientPageRequest) {
		final var managerDto = this.employeeService.findByUsername(username);
		if (Optional.ofNullable(clientPageRequest).isPresent())
			return new ManagerReservationResponse(
					managerDto, 
					this.reservationService.findAllBySaloonId(managerDto.getSaloonDto().getId(), clientPageRequest));
		else
			return new ManagerReservationResponse(
					managerDto, 
					new PageImpl<>(this.reservationService.findAllBySaloonId(managerDto.getSaloonDto().getId())));
	}
	
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
			throw new ReservationAlreadyCompletedException("Reservation is already cancelled");
		else if (reservation.getStatus().equals(ReservationStatus.OUTDATED))
			throw new ReservationAlreadyCompletedException("Reservation is already outdated");
		
		// update
		reservation.setCancelDate(LocalDateTime.now());
		reservation.setStatus(ReservationStatus.CANCELLED);
		
		return ReservationMapper.map(this.reservationService.getReservationRepository().save(reservation));
	}
	
	@Override
	public ManagerReservationResponse searchAllBySaloonIdLikeKey(final String username, final String key) {
		final var managerDto = this.employeeService.findByUsername(username);
		return new ManagerReservationResponse(
				managerDto, 
				new PageImpl<>(this.reservationService.getReservationRepository()
						.searchAllBySaloonIdLikeKey(managerDto.getSaloonDto().getId(), key.strip().toLowerCase())
						.stream()
							.map(ReservationMapper::map)
							.distinct()
							.collect(Collectors.toUnmodifiableList())));
	}
	
	@Override
	public ReservationSubWorkerResponse getAllUnassignedSubWorkers(final String username, final Integer reservationId) {

		final var managerDto = this.employeeService.findByUsername(username);
		final var assignedWorkersIds = this.taskService
				.findAllByReservationId(reservationId)
					.stream()
						.map(TaskDto::getWorkerId)
						.distinct()
						.collect(Collectors.toUnmodifiableSet());
		final var unassignedWorkerDtos = this.employeeService
				.findAllByManagerId(managerDto.getId())
					.stream()
						.filter(w -> !assignedWorkersIds.contains(w.getId()))
						.distinct()
						.collect(Collectors.toUnmodifiableList());
		
		return new ReservationSubWorkerResponse(this.reservationService.findById(reservationId), new PageImpl<>(unassignedWorkerDtos));
	}
	
	
	
}

















