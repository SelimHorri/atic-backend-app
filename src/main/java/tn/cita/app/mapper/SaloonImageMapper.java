package tn.cita.app.mapper;

import javax.validation.constraints.NotNull;

import tn.cita.app.domain.entity.Saloon;
import tn.cita.app.domain.entity.SaloonImage;
import tn.cita.app.dto.SaloonDto;
import tn.cita.app.dto.SaloonImageDto;

public interface SaloonImageMapper {
	
	public static SaloonImageDto map(@NotNull final SaloonImage saloonImage) {
		return SaloonImageDto.builder()
				.id(saloonImage.getId())
				.identifier(saloonImage.getIdentifier())
				.name(saloonImage.getName())
				.type(saloonImage.getType())
				.size(saloonImage.getSize())
				.imageLob(saloonImage.getImageLob())
				.saloonDto(
					SaloonDto.builder()
						.id(saloonImage.getSaloon().getId())
						.identifier(saloonImage.getSaloon().getIdentifier())
						.code(saloonImage.getSaloon().getCode())
						.taxRef(saloonImage.getSaloon().getTaxRef())
						.name(saloonImage.getSaloon().getName())
						.isPrimary(saloonImage.getSaloon().getIsPrimary())
						.openingDate(saloonImage.getSaloon().getOpeningDate())
						.fullAdr(saloonImage.getSaloon().getFullAdr())
						.email(saloonImage.getSaloon().getEmail())
						.build())
				.build();
	}
	
	public static SaloonImage map(@NotNull final SaloonImageDto saloonImageDto) {
		return SaloonImage.builder()
				.id(saloonImageDto.getId())
				.identifier(saloonImageDto.getIdentifier())
				.name(saloonImageDto.getName())
				.type(saloonImageDto.getType())
				.size(saloonImageDto.getSize())
				.imageLob(saloonImageDto.getImageLob())
				.saloon(
					Saloon.builder()
						.id(saloonImageDto.getSaloonDto().getId())
						.identifier(saloonImageDto.getSaloonDto().getIdentifier())
						.code(saloonImageDto.getSaloonDto().getCode())
						.taxRef(saloonImageDto.getSaloonDto().getTaxRef())
						.name(saloonImageDto.getSaloonDto().getName())
						.isPrimary(saloonImageDto.getSaloonDto().getIsPrimary())
						.openingDate(saloonImageDto.getSaloonDto().getOpeningDate())
						.fullAdr(saloonImageDto.getSaloonDto().getFullAdr())
						.email(saloonImageDto.getSaloonDto().getEmail())
						.build())
				.build();
	}
	
	
	
}














