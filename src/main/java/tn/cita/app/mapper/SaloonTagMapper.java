package tn.cita.app.mapper;

import javax.validation.constraints.NotNull;

import tn.cita.app.domain.entity.Saloon;
import tn.cita.app.domain.entity.SaloonTag;
import tn.cita.app.domain.entity.Tag;
import tn.cita.app.dto.SaloonDto;
import tn.cita.app.dto.SaloonTagDto;
import tn.cita.app.dto.TagDto;

public interface SaloonTagMapper {
	
	public static SaloonTagDto map(@NotNull final SaloonTag saloonTag) {
		return SaloonTagDto.builder()
				.saloonId(saloonTag.getSaloonId())
				.tagId(saloonTag.getTagId())
				.taggedDate(saloonTag.getTaggedDate())
				.saloonDto(
					SaloonDto.builder()
						.id(saloonTag.getSaloon().getId())
						.code(saloonTag.getSaloon().getCode())
						.name(saloonTag.getSaloon().getName())
						.isPrimary(saloonTag.getSaloon().getIsPrimary())
						.openingDate(saloonTag.getSaloon().getOpeningDate())
						.fullAdr(saloonTag.getSaloon().getFullAdr())
						.email(saloonTag.getSaloon().getEmail())
						.build())
				.tagDto(
					TagDto.builder()
						.id(saloonTag.getTag().getId())
						.name(saloonTag.getTag().getName())
						.description(saloonTag.getTag().getDescription())
						.build())
				.build();
	}
	
	public static SaloonTag map(@NotNull final SaloonTagDto saloonTagDto) {
		return SaloonTag.builder()
				.saloonId(saloonTagDto.getSaloonId())
				.tagId(saloonTagDto.getTagId())
				.taggedDate(saloonTagDto.getTaggedDate())
				.saloon(
					Saloon.builder()
						.id(saloonTagDto.getSaloonDto().getId())
						.code(saloonTagDto.getSaloonDto().getCode())
						.name(saloonTagDto.getSaloonDto().getName())
						.isPrimary(saloonTagDto.getSaloonDto().getIsPrimary())
						.openingDate(saloonTagDto.getSaloonDto().getOpeningDate())
						.fullAdr(saloonTagDto.getSaloonDto().getFullAdr())
						.email(saloonTagDto.getSaloonDto().getEmail())
						.build())
				.tag(
					Tag.builder()
						.id(saloonTagDto.getTagDto().getId())
						.name(saloonTagDto.getTagDto().getName())
						.description(saloonTagDto.getTagDto().getDescription())
						.build())
				.build();
	}
	
	
	
}














