package tn.cita.app.service;

import org.springframework.data.domain.Page;
import tn.cita.app.model.dto.SaloonDto;
import tn.cita.app.model.dto.request.ClientPageRequest;

public interface SaloonService {
	
	Page<SaloonDto> findAll(final ClientPageRequest clientPageRequest);
	Page<SaloonDto> findAllByLocationState(final String state, final ClientPageRequest clientPageRequest);
	SaloonDto findById(final Integer id);
	SaloonDto findByIdentifier(final String identifier);
	Page<SaloonDto> findAllByCode(final String code);
	
}



