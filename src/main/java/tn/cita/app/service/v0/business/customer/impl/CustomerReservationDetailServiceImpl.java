package tn.cita.app.service.v0.business.customer.impl;

import org.springframework.data.domain.PageImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.dto.ReservationDto;
import tn.cita.app.dto.request.ReservationDetailRequest;
import tn.cita.app.dto.response.ReservationDetailResponse;
import tn.cita.app.exception.wrapper.ReservationNotFoundException;
import tn.cita.app.mapper.ReservationMapper;
import tn.cita.app.service.v0.OrderedDetailService;
import tn.cita.app.service.v0.ReservationService;
import tn.cita.app.service.v0.TaskService;
import tn.cita.app.service.v0.business.customer.CustomerReservationDetailService;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class CustomerReservationDetailServiceImpl implements CustomerReservationDetailService {
	
	private final ReservationService reservationService;
	private final OrderedDetailService orderedDetailService;
	private final TaskService taskService;
	
	@Override
	public ReservationDetailResponse fetchReservationDetails(final Integer reservationId) {
		log.info("** Fetch reservation details by reservationId by customer.. *\n");
		final var reservationDto = this.reservationService.findById(reservationId);
		return ReservationDetailResponse.builder()
				.reservationDto(reservationDto)
				.orderedDetailDtos(new PageImpl<>(this.orderedDetailService
						.findAllByReservationId(reservationDto.getId())))
				.taskDtos(new PageImpl<>(this.taskService
						.findAllByReservationId(reservationDto.getId())))
				.build();
	}
	
	@Transactional
	@Override
	public ReservationDto updateReservationDetails(final ReservationDetailRequest reservationDetailRequest) {
		log.info("** Update reservation details by customer.. *\n");
		final var reservation = this.reservationService.getReservationRepository()
				.findById(reservationDetailRequest.getReservationId())
				.orElseThrow(() -> new ReservationNotFoundException(String
						.format("Reservation with id: %s not found", reservationDetailRequest.getReservationId())));
		reservation.setDescription((reservationDetailRequest.getDescription() == null 
					|| reservationDetailRequest.getDescription().isBlank()) ? 
				null : reservationDetailRequest.getDescription().strip());
		
		return ReservationMapper.map(this.reservationService.getReservationRepository().save(reservation));
	}
	
	
	
}
















