package tn.cita.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.OrderedDetailDto;
import tn.cita.app.mapper.OrderedDetailMapper;
import tn.cita.app.repository.OrderedDetailRepository;
import tn.cita.app.service.OrderedDetailService;

@Service
@Transactional
@RequiredArgsConstructor
public class OrderedDetailServiceImpl implements OrderedDetailService {
	
	private final OrderedDetailRepository orderedDetailRepository;
	
	@Override
	public List<OrderedDetailDto> findAllByReservationId(final Integer reservationId) {
		return this.orderedDetailRepository.findAllByReservationId(reservationId)
				.stream()
					.map(OrderedDetailMapper::map)
					.distinct()
					.collect(Collectors.toUnmodifiableList());
	}
	
}
















