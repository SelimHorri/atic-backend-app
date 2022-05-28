package tn.cita.app.service.v0;

import java.util.List;

import org.springframework.data.domain.Page;

import tn.cita.app.dto.LocationDto;
import tn.cita.app.dto.request.ClientPageRequest;

public interface LocationService {
	
	Page<LocationDto> findAll(final ClientPageRequest clientPageRequest);
	LocationDto findById(final Integer id);
	List<String> getAllCities();
	List<String> getAllStates();
	
}













