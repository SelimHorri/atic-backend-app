package tn.cita.app.service.impl;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.exception.wrapper.LocationNotFoundException;
import tn.cita.app.mapper.LocationMapper;
import tn.cita.app.model.domain.entity.Location;
import tn.cita.app.model.dto.LocationDto;
import tn.cita.app.model.dto.request.ClientPageRequest;
import tn.cita.app.repository.LocationRepository;
import tn.cita.app.service.LocationService;

@Service
@Transactional(readOnly = true)
@Slf4j
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
	
	private final LocationRepository locationRepository;
	
	@Override
	public Page<LocationDto> findAll(final ClientPageRequest clientPageRequest) {
		log.info("** Find all paged locations.. *\n");
		return this.locationRepository.findAll(PageRequest
					.of(clientPageRequest.getOffset() - 1, clientPageRequest.getSize()))
				.map(LocationMapper::map);
	}
	
	@Override
	public LocationDto findById(final Integer id) {
		log.info("** Find location by id.. *\n");
		return this.locationRepository.findById(id)
				.map(LocationMapper::map)
				.orElseThrow(() -> new LocationNotFoundException("Location with id: %d not found"));
	}
	
	@Override
	public List<String> fetchAllCities() {
		log.info("** Fetch all cities.. *\n");
		return this.locationRepository.findAll().stream()
				.map(Location::getCity)
				.map(String::toLowerCase)
				.distinct()
				.toList();
	}
	
	@Override
	public List<String> fetchAllStates() {
		log.info("** Fetch all states.. *\n");
		return this.locationRepository.findAll().stream()
				.map(Location::getState)
				.map(String::toLowerCase)
				.distinct()
				.toList();
	}
	
}









