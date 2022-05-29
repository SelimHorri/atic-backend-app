package tn.cita.app.service.v0.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.ReservationDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.dto.request.ReservationDetailRequest;
import tn.cita.app.dto.response.ReservationDetailResponse;
import tn.cita.app.exception.wrapper.ReservationNotFoundException;
import tn.cita.app.mapper.ReservationMapper;
import tn.cita.app.repository.ReservationRepository;
import tn.cita.app.service.v0.OrderedDetailService;
import tn.cita.app.service.v0.ReservationService;
import tn.cita.app.service.v0.TaskService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
	
	private final ReservationRepository reservationRepository;
	private final OrderedDetailService orderedDetailService;
	private final TaskService taskService;
	
	@Override
	public Page<ReservationDto> findAllByCustomerId(final Integer customerId, final ClientPageRequest clientPageRequest) {
		return this.reservationRepository.findAllByCustomerId(customerId, 
					PageRequest.of(clientPageRequest.getOffset() - 1, clientPageRequest.getSize()))
				.map(ReservationMapper::map);
	}
	
	@Override
	public ReservationDto findById(final Integer id) {
		return this.reservationRepository.findById(id)
				.map(ReservationMapper::map)
				.orElseThrow(() -> new ReservationNotFoundException(String
						.format("Reservation with id: %s not found", id)));
	}
	
	@Override
	public ReservationDto findByCode(final String code) {
		return this.reservationRepository.findByCode(code)
				.map(ReservationMapper::map)
				.orElseThrow(() -> new ReservationNotFoundException(String
						.format("Reservation with code: %s not found", code)));
	}
	
	@Override
	public ReservationDetailResponse getReservationDetails(final Integer reservationId) {
		
		final var reservationDto = this.findById(reservationId);
		
		return ReservationDetailResponse.builder()
				.reservationDto(reservationDto)
				.orderedDetailDtos(this.orderedDetailService.findAllByReservationId(reservationDto.getId()))
				.taskDtos(this.taskService.findAllByReservationId(reservationDto.getId()))
				.build();
	}
	
	@Transactional
	@Override
	public ReservationDto updateReservationDetails(final ReservationDetailRequest reservationDetailRequest) {
		final var reservation = this.reservationRepository.findById(reservationDetailRequest.getReservationId())
				.orElseThrow(() -> new ReservationNotFoundException(String
						.format("Reservation with id: %s not found", reservationDetailRequest.getReservationId())));
		reservation.setDescription(reservationDetailRequest.getDescription());
		
		return ReservationMapper.map(reservation);
	}
	
	@Transactional
	@Override
	public ReservationDto cancelReservation(final ReservationDto reservationDtoRequest) {
		
		final var reservation = this.reservationRepository.findById(reservationDtoRequest.getId())
				.orElseThrow(() -> new ReservationNotFoundException(String
						.format("Reservation with id: %s not found", reservationDtoRequest.getId())));
		
		// update
		reservation.setCancelDate(reservationDtoRequest.getCancelDate());
		reservation.setStatus(reservationDtoRequest.getStatus());
		
		return ReservationMapper.map(this.reservationRepository.save(reservation));
	}
	
	
	
}














