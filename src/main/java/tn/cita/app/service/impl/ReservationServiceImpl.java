package tn.cita.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.ReservationDto;
import tn.cita.app.dto.response.ReservationContainerResponse;
import tn.cita.app.exception.wrapper.ReservationNotFoundException;
import tn.cita.app.mapper.ReservationMapper;
import tn.cita.app.repository.ReservationRepository;
import tn.cita.app.service.OrderedDetailService;
import tn.cita.app.service.ReservationService;
import tn.cita.app.service.TaskService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
	
	private final ReservationRepository reservationRepository;
	private final OrderedDetailService orderedDetailService;
	private final TaskService taskService;
	
	@Override
	public List<ReservationDto> findAllByCustomerId(final Integer customerId) {
		return this.reservationRepository.findAllByCustomerId(customerId)
				.stream()
					.map(ReservationMapper::map)
					.distinct()
					.collect(Collectors.toUnmodifiableList());
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
	public ReservationContainerResponse getReservationDetails(final Integer reservationId) {
		
		final var reservationDto = this.findById(reservationId);
		
		return ReservationContainerResponse.builder()
				.reservationDto(reservationDto)
				.orderedDetailDtos(this.orderedDetailService.findAllByReservationId(reservationDto.getId()))
				.taskDtos(this.taskService.findAllByReservationId(reservationDto.getId()))
				.build();
	}
	
	
	
}














