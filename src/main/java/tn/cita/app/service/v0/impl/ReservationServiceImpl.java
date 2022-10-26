package tn.cita.app.service.v0.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.dto.ReservationDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.exception.wrapper.ReservationNotFoundException;
import tn.cita.app.mapper.ReservationMapper;
import tn.cita.app.repository.ReservationRepository;
import tn.cita.app.service.v0.ReservationService;
import tn.cita.app.util.ClientRequestUtils;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
	
	private final ReservationRepository reservationRepository;
	
	@Override
	public ReservationRepository getReservationRepository() {
		return this.reservationRepository;
	}
	
	@Override
	public Page<ReservationDto> findAllByCustomerId(final Integer customerId, final ClientPageRequest clientPageRequest) {
		log.info("** Find all reservations by customerId.. *\n");
		return this.reservationRepository.findAllByCustomerId(customerId, 
					PageRequest.of(clientPageRequest.getOffset() - 1, clientPageRequest.getSize(), 
							Sort.by(clientPageRequest.getSortDirection(), clientPageRequest.getSortBy())))
				.map(ReservationMapper::map);
	}
	
	@Override
	public ReservationDto findById(final Integer id) {
		log.info("** Find reservation by id.. *\n");
		return this.reservationRepository.findById(id)
				.map(ReservationMapper::map)
				.orElseThrow(() -> new ReservationNotFoundException("Reservation not found"));
	}
	
	@Override
	public ReservationDto findByCode(final String code) {
		log.info("** Find reservation by code.. *\n");
		return this.reservationRepository.findByCode(code)
				.map(ReservationMapper::map)
				.orElseThrow(() -> new ReservationNotFoundException(String
						.format("Reservation with code: %s not found", code)));
	}
	
	@Override
	public Page<ReservationDto> findAllBySaloonId(final Integer saloonId, final ClientPageRequest clientPageRequest) {
		log.info("** Find all paged reservations by saloonId.. *\n");
		return this.reservationRepository.findAllBySaloonId(saloonId, 
					ClientRequestUtils.from(clientPageRequest))
				.map(ReservationMapper::map);
	}
	
	@Override
	public List<ReservationDto> findAllBySaloonId(final Integer saloonId) {
		log.info("** Find all reservations by saloonId.. *\n");
		return this.reservationRepository.findAllBySaloonId(saloonId).stream()
				.map(ReservationMapper::map)
				.distinct()
				.collect(Collectors.toUnmodifiableList());
	}
	
	
	
}














