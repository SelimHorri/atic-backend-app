package tn.cita.app.mapper;

import javax.validation.constraints.NotNull;

import tn.cita.app.domain.entity.Saloon;
import tn.cita.app.domain.entity.SaloonImage;
import tn.cita.app.dto.SaloonImageDto;

public interface SaloonImageMapper {
	
	public static SaloonImageDto map(@NotNull final SaloonImage saloonImage) {
		return SaloonImageDto.builder()
				.id(saloonImage.getId())
				.name(saloonImage.getName())
				.type(saloonImage.getType())
				.size(saloonImage.getSize())
				.imageLob(saloonImage.getImageLob())
				.saloonId(saloonImage.getSaloon().getId())
				.build();
	}
	
	public static SaloonImage map(@NotNull final SaloonImageDto saloonImageDto) {
		return SaloonImage.builder()
				.id(saloonImageDto.getId())
				.name(saloonImageDto.getName())
				.type(saloonImageDto.getType())
				.size(saloonImageDto.getSize())
				.imageLob(saloonImageDto.getImageLob())
				.saloon(
					Saloon.builder()
						.id(saloonImageDto.getSaloonId())
						.build())
				.build();
	}
	
	
	
}














