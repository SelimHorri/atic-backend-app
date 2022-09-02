package tn.cita.app.service.v0.business.employee.worker.impl;

import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.dto.response.ReservationDetailResponse;
import tn.cita.app.service.v0.OrderedDetailService;
import tn.cita.app.service.v0.ReservationService;
import tn.cita.app.service.v0.TaskService;
import tn.cita.app.service.v0.business.employee.worker.WorkerReservationDetailService;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class WorkerReservationDetailServiceImpl implements WorkerReservationDetailService {
	
	private final TaskService taskService;
	private final ReservationService reservationService;
	private final OrderedDetailService orderedDetailService;
	
	@Override
	public ReservationDetailResponse fetchReservationDetails(final Integer reservationId) {
		log.info("** Fetch reservation details by reservationId by worker.. *\n");
		final var reservationDto = this.reservationService.findById(reservationId);
		return ReservationDetailResponse.builder()
				.reservationDto(reservationDto)
				.orderedDetailDtos(new PageImpl<>(this.orderedDetailService
						.findAllByReservationId(reservationDto.getId())))
				.taskDtos(new PageImpl<>(this.taskService
						.findAllByReservationId(reservationDto.getId())))
				.build();
	}
	
	
	
}














