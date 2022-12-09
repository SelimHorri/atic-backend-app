package tn.cita.app.service.v0.business.employee.manager.impl;

import java.util.Comparator;

import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.exception.wrapper.ReservationNotFoundException;
import tn.cita.app.mapper.OrderedDetailMapper;
import tn.cita.app.mapper.ReservationMapper;
import tn.cita.app.mapper.TaskMapper;
import tn.cita.app.model.domain.ReservationStatus;
import tn.cita.app.model.dto.TaskDto;
import tn.cita.app.model.dto.request.ReservationAssignWorkerRequest;
import tn.cita.app.model.dto.response.ReservationBeginEndTask;
import tn.cita.app.model.dto.response.ReservationDetailResponse;
import tn.cita.app.model.dto.response.ReservationSubWorkerResponse;
import tn.cita.app.repository.OrderedDetailRepository;
import tn.cita.app.repository.ReservationRepository;
import tn.cita.app.repository.TaskRepository;
import tn.cita.app.service.v0.business.employee.manager.ManagerReservationDetailService;
import tn.cita.app.service.v0.common.ReservationCommonService;

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
		final var reservationDto = this.reservationRepository.findById(reservationId)
				.map(ReservationMapper::map)
				.orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));
		return ReservationDetailResponse.builder()
				.reservationDto(reservationDto)
				.orderedDetailDtos(new PageImpl<>(this.orderedDetailRepository
						.findAllByReservationId(reservationId).stream()
						.map(OrderedDetailMapper::map)
						.distinct()
						.toList()))
				.build();
	}
	
	@Override
	public ReservationBeginEndTask fetchBeginEndTask(final Integer reservationId) {
		
		log.info("** Fetch begin end task by manager.. *\n");
		
		final var taskDtos = this.taskRepository
				.findAllByReservationId(reservationId).stream()
					.map(TaskMapper::map)
					.distinct()
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
















