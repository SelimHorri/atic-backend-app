package tn.cita.app.business.reservation;

import org.springframework.data.domain.Page;
import tn.cita.app.model.dto.ReservationDto;
import tn.cita.app.model.dto.request.ClientPageRequest;

import java.util.List;

public interface ReservationService {
	
	Page<ReservationDto> findAllByCustomerId(final Integer customerId, final ClientPageRequest clientPageRequest);
	ReservationDto findById(final Integer id);
	ReservationDto findByIdentifier(final String identifier);
	ReservationDto findByCode(final String code);
	List<ReservationDto> findAllBySaloonId(final Integer saloonId);
	Page<ReservationDto> findAllBySaloonId(final Integer saloonId, final ClientPageRequest clientPageRequest);
	
}



