package tn.cita.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.domain.entity.OrderedDetail;
import tn.cita.app.domain.id.OrderedDetailId;
import tn.cita.app.dto.OrderedDetailDto;
import tn.cita.app.dto.request.OrderedDetailRequest;
import tn.cita.app.exception.wrapper.OrderedDetailAlreadyExistsException;
import tn.cita.app.exception.wrapper.ReservationNotFoundException;
import tn.cita.app.exception.wrapper.ServiceDetailNotFoundException;
import tn.cita.app.mapper.OrderedDetailMapper;
import tn.cita.app.repository.OrderedDetailRepository;
import tn.cita.app.repository.ReservationRepository;
import tn.cita.app.repository.ServiceDetailRepository;
import tn.cita.app.service.OrderedDetailService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderedDetailServiceImpl implements OrderedDetailService {
	
	private final OrderedDetailRepository orderedDetailRepository;
	private final ReservationRepository reservationRepository;
	private final ServiceDetailRepository serviceDetailRepository;
	
	@Override
	public List<OrderedDetailDto> findAllByReservationId(final Integer reservationId) {
		return this.orderedDetailRepository.findAllByReservationId(reservationId)
				.stream()
					.map(OrderedDetailMapper::map)
					.distinct()
					.collect(Collectors.toUnmodifiableList());
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
		
		/*
		this.orderedDetailRepository.findById(orderedDetailId).ifPresent(e -> {
			throw new OrderedDetailAlreadyExistsException(String
					.format("Service is already ordered"));
		});
		*/
		if (this.orderedDetailRepository.existsById(orderedDetailId))
			throw new OrderedDetailAlreadyExistsException(String
					.format("Service is already ordered"));
		
		final var orderedDetail = OrderedDetail.builder()
				.reservationId(orderedDetailId.getReservationId())
				.serviceDetailId(orderedDetailId.getServiceDetailId())
				.reservation(this.reservationRepository.findById(orderedDetailId.getReservationId())
						.orElseThrow(ReservationNotFoundException::new))
				.serviceDetail(this.serviceDetailRepository.findById(orderedDetailId.getServiceDetailId())
						.orElseThrow(ServiceDetailNotFoundException::new))
				.build();
		
		System.err.println(OrderedDetailMapper.map(orderedDetail));
		
		return OrderedDetailMapper.map(this.orderedDetailRepository.save(orderedDetail));
	}
	
	
	
}
















