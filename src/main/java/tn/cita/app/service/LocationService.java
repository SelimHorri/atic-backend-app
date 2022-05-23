package tn.cita.app.service;

import java.util.List;

import org.springframework.data.domain.Page;

import tn.cita.app.dto.LocationDto;

public interface LocationService {
	
	Page<LocationDto> findAll(final int offset);
	LocationDto findById(final Integer id);
	List<String> getAllCities();
	List<String> getAllStates();
	
}













