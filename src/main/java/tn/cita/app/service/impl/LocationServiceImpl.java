package tn.cita.app.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstant;
import tn.cita.app.dto.LocationDto;
import tn.cita.app.exception.wrapper.LocationNotFoundException;
import tn.cita.app.mapper.LocationMapper;
import tn.cita.app.repository.LocationRepository;
import tn.cita.app.service.LocationService;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
	
	private final LocationRepository locationRepository;
	
	@Override
	public List<LocationDto> findAll(final int offset) {
		return this.locationRepository.findAll(PageRequest.of(offset - 1, AppConstant.PAGE_SIZE))
				.stream()
					.map(LocationMapper::map)
					.distinct()
					.collect(Collectors.toUnmodifiableList());
	}
	
	@Override
	public LocationDto findById(final Integer id) {
		return this.locationRepository.findById(id)
				.map(LocationMapper::map)
				.orElseThrow(() -> new LocationNotFoundException(String
						.format("Location with id: %d not found", id)));
	}
	
	
	
}














