package tn.cita.app.service;

import java.util.List;

import tn.cita.app.domain.id.OrderedDetailId;
import tn.cita.app.dto.OrderedDetailDto;

public interface OrderedDetailService {
	
	List<OrderedDetailDto> findAllByReservationId(final Integer reservationId);
	Boolean deleteById(final OrderedDetailId orderedDetailId);
	
}












