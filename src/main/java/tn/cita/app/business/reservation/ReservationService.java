package tn.cita.app.business.reservation;

import java.util.List;

import org.springframework.data.domain.Page;

import tn.cita.app.model.dto.ReservationDto;
import tn.cita.app.model.dto.request.ClientPageRequest;

public interface ReservationService {
	
	Page<ReservationDto> findAllByCustomerId(final Integer customerId, final ClientPageRequest clientPageRequest);
	ReservationDto findById(final Integer id);
	ReservationDto findByIdentifier(final String identifier);
	ReservationDto findByCode(final String code);
	List<ReservationDto> findAllBySaloonId(final Integer saloonId);
	Page<ReservationDto> findAllBySaloonId(final Integer saloonId, final ClientPageRequest clientPageRequest);
	
}





