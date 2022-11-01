package tn.cita.app.mapper;

import javax.validation.constraints.NotNull;

import tn.cita.app.model.domain.entity.Location;
import tn.cita.app.model.dto.LocationDto;

public interface LocationMapper {
	
	public static LocationDto map(@NotNull final Location location) {
		return LocationDto.builder()
				.id(location.getId())
				.identifier(location.getIdentifier())
				.zipcode(location.getZipcode())
				.city(location.getCity())
				.state(location.getState())
				.build();
	}
	
	public static Location map(@NotNull final LocationDto locationDto) {
		return Location.builder()
				.id(locationDto.getId())
				.identifier(locationDto.getIdentifier())
				.zipcode(locationDto.getZipcode())
				.city(locationDto.getCity())
				.state(locationDto.getState())
				.build();
	}
	
	
	
}












