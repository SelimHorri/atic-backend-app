package tn.cita.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.ReservationDto;
import tn.cita.app.mapper.ReservationMapper;
import tn.cita.app.repository.ReservationRepository;
import tn.cita.app.service.ReservationService;

@Service
@Transactional
@RequiredArgsConstructor
public class ReservationServiceImpl implements ReservationService {
	
	private final ReservationRepository reservationRepository;
	
	@Transactional(readOnly = true)
	@Override
	public List<ReservationDto> findAllByCustomerId(final Integer customerId) {
		return this.reservationRepository.findAllByCustomerId(customerId)
				.stream()
					.map(ReservationMapper::map)
					.distinct()
					.collect(Collectors.toUnmodifiableList());
	}
	
	
	
}














