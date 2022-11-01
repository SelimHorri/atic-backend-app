package tn.cita.app.service.v0.business.employee.worker.impl;

import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.exception.wrapper.ReservationNotFoundException;
import tn.cita.app.mapper.OrderedDetailMapper;
import tn.cita.app.mapper.ReservationMapper;
import tn.cita.app.mapper.TaskMapper;
import tn.cita.app.model.dto.response.ReservationDetailResponse;
import tn.cita.app.repository.OrderedDetailRepository;
import tn.cita.app.repository.ReservationRepository;
import tn.cita.app.repository.TaskRepository;
import tn.cita.app.service.v0.business.employee.worker.WorkerReservationDetailService;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class WorkerReservationDetailServiceImpl implements WorkerReservationDetailService {
	
	private final TaskRepository taskRepository;
	private final ReservationRepository reservationRepository;
	private final OrderedDetailRepository orderedDetailRepository;
	
	@Override
	public ReservationDetailResponse fetchReservationDetails(final Integer reservationId) {
		log.info("** Fetch reservation details by reservationId by worker.. *\n");
		final var reservationDto = this.reservationRepository.findById(reservationId)
				.map(ReservationMapper::map)
				.orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));
		return ReservationDetailResponse.builder()
				.reservationDto(reservationDto)
				.orderedDetailDtos(new PageImpl<>(this.orderedDetailRepository
						.findAllByReservationId(reservationDto.getId()).stream()
						.map(OrderedDetailMapper::map)
						.distinct()
						.toList()))
				.taskDtos(new PageImpl<>(this.taskRepository
						.findAllByReservationId(reservationDto.getId()).stream()
						.map(TaskMapper::map)
						.distinct()
						.toList()))
				.build();
	}
	
	
	
}














