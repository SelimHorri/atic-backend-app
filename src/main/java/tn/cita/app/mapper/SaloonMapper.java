package tn.cita.app.mapper;

import javax.validation.constraints.NotNull;

import tn.cita.app.domain.entity.Location;
import tn.cita.app.domain.entity.Saloon;
import tn.cita.app.dto.LocationDto;
import tn.cita.app.dto.SaloonDto;

public interface SaloonMapper {
	
	public static SaloonDto map(@NotNull final Saloon saloon) {
		return SaloonDto.builder()
				.id(saloon.getId())
				.code(saloon.getCode())
				.name(saloon.getName())
				.isPrimary(saloon.getIsPrimary())
				.openingDate(saloon.getOpeningDate())
				.fullAdr(saloon.getFullAdr())
				.email(saloon.getEmail())
				.locationDto(
					LocationDto.builder()
						.id(saloon.getId())
						.zipcode(saloon.getLocation().getZipcode())
						.city(saloon.getLocation().getCity())
						.state(saloon.getLocation().getState())
						.build())
				.build();
	}
	
	public static Saloon map(@NotNull final SaloonDto saloonDto) {
		return Saloon.builder()
				.id(saloonDto.getId())
				.code(saloonDto.getCode())
				.name(saloonDto.getName())
				.isPrimary(saloonDto.getIsPrimary())
				.openingDate(saloonDto.getOpeningDate())
				.fullAdr(saloonDto.getFullAdr())
				.email(saloonDto.getEmail())
				.location(
					Location.builder()
						.id(saloonDto.getId())
						.zipcode(saloonDto.getLocationDto().getZipcode())
						.city(saloonDto.getLocationDto().getCity())
						.state(saloonDto.getLocationDto().getState())
						.build())
				.build();
	}
	
	
	
}














