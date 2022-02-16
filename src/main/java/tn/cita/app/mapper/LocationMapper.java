package tn.cita.app.mapper;

import javax.validation.constraints.NotNull;

import tn.cita.app.domain.entity.Location;
import tn.cita.app.dto.LocationDto;

public interface LocationMapper {
	
	public static LocationDto map(@NotNull final Location location) {
		return LocationDto.builder()
				.id(location.getId())
				.zipcode(location.getZipcode())
				.city(location.getCity())
				.state(location.getState())
				.build();
	}
	
	public static Location map(@NotNull final LocationDto locationDto) {
		return Location.builder()
				.id(locationDto.getId())
				.zipcode(locationDto.getZipcode())
				.city(locationDto.getCity())
				.state(locationDto.getState())
				.build();
	}
	
	
	
}











