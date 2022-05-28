package tn.cita.app.service.v0;

import org.springframework.data.domain.Page;

import tn.cita.app.domain.id.OrderedDetailId;
import tn.cita.app.dto.OrderedDetailDto;
import tn.cita.app.dto.request.OrderedDetailRequest;

public interface OrderedDetailService {
	
	Page<OrderedDetailDto> findAllByReservationId(final Integer reservationId);
	Boolean deleteById(final OrderedDetailId orderedDetailId);
	OrderedDetailDto save(final OrderedDetailRequest orderedDetailRequest);
	
}












