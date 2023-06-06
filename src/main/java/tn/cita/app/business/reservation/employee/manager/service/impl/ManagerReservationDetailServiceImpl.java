package tn.cita.app.business.reservation.employee.manager.service.impl;

import java.util.Comparator;

import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.business.reservation.ReservationCommonService;
import tn.cita.app.business.reservation.employee.manager.model.ReservationAssignWorkerRequest;
import tn.cita.app.business.reservation.employee.manager.model.ReservationSubWorkerResponse;
import tn.cita.app.business.reservation.employee.manager.service.ManagerReservationDetailService;
import tn.cita.app.exception.wrapper.ReservationNotFoundException;
import tn.cita.app.mapper.OrderedDetailMapper;
import tn.cita.app.mapper.ReservationMapper;
import tn.cita.app.mapper.TaskMapper;
import tn.cita.app.model.domain.ReservationStatus;
import tn.cita.app.model.dto.TaskDto;
import tn.cita.app.model.dto.response.ReservationBeginEndTask;
import tn.cita.app.model.dto.response.ReservationDetailResponse;
import tn.cita.app.repository.OrderedDetailRepository;
import tn.cita.app.repository.ReservationRepository;
import tn.cita.app.repository.TaskRepository;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class ManagerReservationDetailServiceImpl implements ManagerReservationDetailService {
	
	private final ReservationRepository reservationRepository;
	private final ReservationCommonService reservationCommonService;
	private final OrderedDetailRepository orderedDetailRepository;
	private final TaskRepository taskRepository;
	
	@Override
	public ReservationDetailResponse fetchReservationDetails(final Integer reservationId) {
		log.info("** Fetch reservation details by reservationId by manager.. *\n");
		final var reservationDto = this.reservationRepository
				.findById(reservationId)
				.map(ReservationMapper::toDto)
				.orElseThrow(ReservationNotFoundException::new);
		return ReservationDetailResponse.builder()
				.reservationDto(reservationDto)
				.orderedDetailDtos(new PageImpl<>(
						this.orderedDetailRepository
								.findAllByReservationId(reservationDto.getId()).stream()
								.map(OrderedDetailMapper::toDto)
								.toList()))
				.build();
	}
	
	@Override
	public ReservationBeginEndTask fetchBeginEndTask(final Integer reservationId) {
		
		log.info("** Fetch begin end task by manager.. *\n");
		
		final var taskDtos = this.taskRepository
				.findAllByReservationId(reservationId).stream()
					.map(TaskMapper::toDto)
					.toList();
		
		final var firstTaskBegin = taskDtos.stream()
				.filter(t -> t.getStartDate() != null)
				// .filter(t -> t.getReservationDto().getStatus().equals(ReservationStatus.IN_PROGRESS))
				.min(Comparator.comparing(TaskDto::getStartDate))
				.orElseGet(TaskDto::new);
		final var lastTaskEnd = taskDtos.stream()
				.filter(t -> t.getEndDate() != null)
				.filter(t -> t.getReservationDto().getStatus().equals(ReservationStatus.COMPLETED))
				.max(Comparator.comparing(TaskDto::getEndDate))
				.orElseGet(TaskDto::new);
		
		return new ReservationBeginEndTask(firstTaskBegin, lastTaskEnd);
	}
	
	@Override
	public ReservationSubWorkerResponse fetchAllUnassignedSubWorkers(final String username, final Integer reservationId) {
		return this.reservationCommonService.fetchAllUnassignedSubWorkers(username, reservationId);
	}
	
	@Transactional
	@Override
	public ReservationSubWorkerResponse assignReservationWorkers(final String username, 
			final ReservationAssignWorkerRequest reservationAssignWorkerRequest) {
		return this.reservationCommonService.assignReservationWorkers(username, reservationAssignWorkerRequest);
	}
	
}



