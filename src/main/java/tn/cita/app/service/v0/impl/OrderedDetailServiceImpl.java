package tn.cita.app.service.v0.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.domain.id.OrderedDetailId;
import tn.cita.app.dto.OrderedDetailDto;
import tn.cita.app.dto.request.OrderedDetailRequest;
import tn.cita.app.exception.wrapper.OrderedDetailAlreadyExistsException;
import tn.cita.app.mapper.OrderedDetailMapper;
import tn.cita.app.repository.OrderedDetailRepository;
import tn.cita.app.service.v0.OrderedDetailService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderedDetailServiceImpl implements OrderedDetailService {
	
	private final OrderedDetailRepository orderedDetailRepository;
	
	@Override
	public OrderedDetailRepository getOrderedDetailRepository() {
		return this.orderedDetailRepository;
	}
	
	@Override
	public List<OrderedDetailDto> findAllByReservationId(final Integer reservationId) {
		return this.orderedDetailRepository.findAllByReservationId(reservationId).stream()
				.map(OrderedDetailMapper::map)
				.distinct()
				.collect(Collectors.toList());
	}
	
	@Transactional
	@Override
	public Boolean deleteById(final OrderedDetailId orderedDetailId) {
		this.orderedDetailRepository.deleteById(orderedDetailId);
		return !this.orderedDetailRepository.existsById(orderedDetailId);
	}
	
	@Transactional
	@Override
	public OrderedDetailDto save(final OrderedDetailRequest orderedDetailRequest) {
		
		final OrderedDetailId orderedDetailId = new OrderedDetailId(orderedDetailRequest.getReservationId(), 
				orderedDetailRequest.getServiceDetailId());
		
		if (this.orderedDetailRepository.existsById(orderedDetailId))
			throw new OrderedDetailAlreadyExistsException(String.format("Service is already ordered"));
		
		this.orderedDetailRepository.saveOrderedDetail(orderedDetailRequest);
		return OrderedDetailMapper.map(this.orderedDetailRepository.findById(orderedDetailId).orElseThrow());
	}
	
	
	
}
















