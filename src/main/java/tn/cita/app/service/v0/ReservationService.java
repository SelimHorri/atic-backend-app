package tn.cita.app.service.v0;

import java.util.List;

import org.springframework.data.domain.Page;

import tn.cita.app.dto.ReservationDto;
import tn.cita.app.dto.request.ClientPageRequest;

public interface ReservationService {
	
	Page<ReservationDto> findAllByCustomerId(final Integer customerId, final ClientPageRequest clientPageRequest);
	ReservationDto findById(final Integer id);
	ReservationDto findByCode(final String code);
	List<ReservationDto> findAllBySaloonId(final Integer saloonId);
	Page<ReservationDto> findAllBySaloonId(final Integer saloonId, final ClientPageRequest clientPageRequest);
	
}







