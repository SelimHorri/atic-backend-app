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
				.imageLob(saloonImage.getImageLob())
				.saloonDto(
					SaloonDto.builder()
						.id(saloonImage.getSaloon().getId())
						.code(saloonImage.getSaloon().getCode())
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
				.imageLob(saloonImageDto.getImageLob())
				.saloon(
					Saloon.builder()
						.id(saloonImageDto.getSaloonDto().getId())
						.code(saloonImageDto.getSaloonDto().getCode())
						.name(saloonImageDto.getSaloonDto().getName())
						.isPrimary(saloonImageDto.getSaloonDto().getIsPrimary())
						.openingDate(saloonImageDto.getSaloonDto().getOpeningDate())
						.fullAdr(saloonImageDto.getSaloonDto().getFullAdr())
						.email(saloonImageDto.getSaloonDto().getEmail())
						.build())
				.build();
	}
	
	
	
}













