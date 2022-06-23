package tn.cita.app.service.v0;

import java.util.List;

import tn.cita.app.domain.id.OrderedDetailId;
import tn.cita.app.dto.OrderedDetailDto;
import tn.cita.app.dto.request.OrderedDetailRequest;
import tn.cita.app.repository.OrderedDetailRepository;

public interface OrderedDetailService {
	
	OrderedDetailRepository getOrderedDetailRepository();
	List<OrderedDetailDto> findAllByReservationId(final Integer reservationId);
	Boolean deleteById(final OrderedDetailId orderedDetailId);
	OrderedDetailDto save(final OrderedDetailRequest orderedDetailRequest);
	
}












