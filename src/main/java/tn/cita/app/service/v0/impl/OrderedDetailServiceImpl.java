package tn.cita.app.service.v0.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.exception.wrapper.OrderedDetailAlreadyExistsException;
import tn.cita.app.exception.wrapper.OrderedDetailNotFoundException;
import tn.cita.app.mapper.OrderedDetailMapper;
import tn.cita.app.model.domain.id.OrderedDetailId;
import tn.cita.app.model.dto.OrderedDetailDto;
import tn.cita.app.model.dto.request.OrderedDetailRequest;
import tn.cita.app.repository.OrderedDetailRepository;
import tn.cita.app.service.v0.OrderedDetailService;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class OrderedDetailServiceImpl implements OrderedDetailService {
	
	private final OrderedDetailRepository orderedDetailRepository;
	
	@Override
	public OrderedDetailDto findByIdentifier(final String identifier) {
		return this.orderedDetailRepository.findByIdentifier(identifier)
				.map(OrderedDetailMapper::map)
				.orElseThrow(() -> new OrderedDetailNotFoundException("OrderDetail not found"));
	}
	
	@Override
	public List<OrderedDetailDto> findAllByReservationId(final Integer reservationId) {
		log.info("** Find all ordered detail by reservationId.. *\n");
		return this.orderedDetailRepository.findAllByReservationId(reservationId).stream()
				.map(OrderedDetailMapper::map)
				.distinct()
				.toList();
	}
	
	@Transactional
	@Override
	public Boolean deleteById(final OrderedDetailId orderedDetailId) {
		log.info("** Delete ordered detail by id.. *\n");
		this.orderedDetailRepository.deleteById(orderedDetailId);
		return !this.orderedDetailRepository.existsById(orderedDetailId);
	}
	
	@Transactional
	@Override
	public OrderedDetailDto save(OrderedDetailRequest orderedDetailRequest) {
		
		log.info("** Save ordered detail.. *\n");
		
		final var orderedDetailId = new OrderedDetailId(orderedDetailRequest.reservationId(), 
				orderedDetailRequest.serviceDetailId());
		
		if (this.orderedDetailRepository.existsById(orderedDetailId))
			throw new OrderedDetailAlreadyExistsException("Service is already ordered");
		
		if (orderedDetailRequest.orderedDate() == null)
			orderedDetailRequest = new OrderedDetailRequest(
					orderedDetailId.getReservationId(), 
					orderedDetailId.getServiceDetailId(), 
					LocalDateTime.now());
		
		this.orderedDetailRepository.saveOrderedDetail(orderedDetailRequest);
		return OrderedDetailMapper.map(this.orderedDetailRepository.findById(orderedDetailId).orElseThrow());
	}
	
}









