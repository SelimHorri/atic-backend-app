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
				.identifier(saloon.getIdentifier())
				.code(saloon.getCode())
				.taxRef(saloon.getTaxRef())
				.name(saloon.getName())
				.isPrimary(saloon.getIsPrimary())
				.openingDate(saloon.getOpeningDate())
				.fullAdr(saloon.getFullAdr())
				.iframeGoogleMap(saloon.getIframeGoogleMap())
				.email(saloon.getEmail())
				.locationDto(
					LocationDto.builder()
						.id(saloon.getId())
						.identifier(saloon.getLocation().getIdentifier())
						.zipcode(saloon.getLocation().getZipcode())
						.city(saloon.getLocation().getCity())
						.state(saloon.getLocation().getState())
						.build())
				.build();
	}
	
	public static Saloon map(@NotNull final SaloonDto saloonDto) {
		return Saloon.builder()
				.id(saloonDto.getId())
				.identifier(saloonDto.getIdentifier())
				.code(saloonDto.getCode())
				.taxRef(saloonDto.getTaxRef())
				.name(saloonDto.getName())
				.isPrimary(saloonDto.getIsPrimary())
				.openingDate(saloonDto.getOpeningDate())
				.fullAdr(saloonDto.getFullAdr())
				.iframeGoogleMap(saloonDto.getIframeGoogleMap())
				.email(saloonDto.getEmail())
				.location(
					Location.builder()
						.id(saloonDto.getId())
						.identifier(saloonDto.getLocationDto().getIdentifier())
						.zipcode(saloonDto.getLocationDto().getZipcode())
						.city(saloonDto.getLocationDto().getCity())
						.state(saloonDto.getLocationDto().getState())
						.build())
				.build();
	}
	
	
	
}














