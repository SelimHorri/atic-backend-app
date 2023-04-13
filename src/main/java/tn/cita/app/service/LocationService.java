package tn.cita.app.service;

import java.util.List;

import org.springframework.data.domain.Page;

import tn.cita.app.model.dto.LocationDto;
import tn.cita.app.model.dto.request.ClientPageRequest;

public interface LocationService {
	
	Page<LocationDto> findAll(final ClientPageRequest clientPageRequest);
	LocationDto findById(final Integer id);
	List<String> fetchAllCities();
	List<String> fetchAllStates();
	
}






