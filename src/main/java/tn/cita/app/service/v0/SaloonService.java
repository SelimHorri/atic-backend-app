package tn.cita.app.service.v0;

import org.springframework.data.domain.Page;

import tn.cita.app.dto.SaloonDto;
import tn.cita.app.dto.request.ClientPageRequest;
import tn.cita.app.repository.SaloonRepository;

public interface SaloonService {
	
	SaloonRepository getSaloonRepository();
	Page<SaloonDto> findAll(final ClientPageRequest clientPageRequest);
	Page<SaloonDto> findAllByLocationState(final String state, final ClientPageRequest clientPageRequest);
	SaloonDto findById(final Integer id);
	Page<SaloonDto> findAllByCode(final String code);
	
}












