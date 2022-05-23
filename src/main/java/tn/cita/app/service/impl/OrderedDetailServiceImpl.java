package tn.cita.app.service.impl;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.domain.id.OrderedDetailId;
import tn.cita.app.dto.OrderedDetailDto;
import tn.cita.app.dto.request.OrderedDetailRequest;
import tn.cita.app.exception.wrapper.OrderedDetailAlreadyExistsException;
import tn.cita.app.mapper.OrderedDetailMapper;
import tn.cita.app.repository.OrderedDetailRepository;
import tn.cita.app.service.OrderedDetailService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderedDetailServiceImpl implements OrderedDetailService {
	
	private final OrderedDetailRepository orderedDetailRepository;
	
	@Override
	public Page<OrderedDetailDto> findAllByReservationId(final Integer reservationId) {
		return this.orderedDetailRepository.findAllByReservationId(reservationId, PageRequest.of(1 - 1, AppConstant.PAGE_SIZE))
				.map(OrderedDetailMapper::map);
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
















