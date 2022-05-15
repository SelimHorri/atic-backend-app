package tn.cita.app.service;

import java.util.List;

import tn.cita.app.dto.LocationDto;

public interface LocationService {
	
	List<LocationDto> findAll(final int offset);
	LocationDto findById(final Integer id);
	
}













