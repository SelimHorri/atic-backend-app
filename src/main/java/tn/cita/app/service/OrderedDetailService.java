package tn.cita.app.service;

import java.util.List;

import tn.cita.app.domain.id.OrderedDetailId;
import tn.cita.app.dto.OrderedDetailDto;
import tn.cita.app.dto.request.OrderedDetailRequest;

public interface OrderedDetailService {
	
	List<OrderedDetailDto> findAllByReservationId(final Integer reservationId);
	Boolean deleteById(final OrderedDetailId orderedDetailId);
	OrderedDetailDto save(final OrderedDetailRequest orderedDetailRequest);
	
}












