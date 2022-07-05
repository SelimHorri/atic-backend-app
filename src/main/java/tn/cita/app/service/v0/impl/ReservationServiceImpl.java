package tn.cita.app.service.v0.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.ReservationDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.exception.wrapper.ReservationNotFoundException;
import tn.cita.app.mapper.ReservationMapper;
import tn.cita.app.repository.ReservationRepository;
import tn.cita.app.service.v0.ReservationService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
	
	private final ReservationRepository reservationRepository;
	
	@Override
	public ReservationRepository getReservationRepository() {
		return this.reservationRepository;
	}
	
	@Override
	public Page<ReservationDto> findAllByCustomerId(final Integer customerId, final ClientPageRequest clientPageRequest) {
		return this.reservationRepository.findAllByCustomerId(customerId, 
					PageRequest.of(clientPageRequest.getOffset() - 1, clientPageRequest.getSize(), 
							Sort.by(clientPageRequest.getSortDirection(), clientPageRequest.getSortBy())))
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
	public Page<ReservationDto> findAllBySaloonId(final Integer saloonId, final ClientPageRequest clientPageRequest) {
		return this.reservationRepository.findAllBySaloonId(saloonId, 
					PageRequest.of(clientPageRequest.getOffset() - 1, clientPageRequest.getSize(), 
							clientPageRequest.getSortDirection(), clientPageRequest.getSortBy()))
				.map(ReservationMapper::map);
	}
	
	@Override
	public List<ReservationDto> findAllBySaloonId(final Integer saloonId) {
		return this.reservationRepository.findAllBySaloonId(saloonId)
				.stream()
					.map(ReservationMapper::map)
					.distinct()
					.collect(Collectors.toUnmodifiableList());
	}
	
	
	
}














