package tn.cita.app.mapper;

import javax.validation.constraints.NotNull;

import tn.cita.app.model.domain.entity.Saloon;
import tn.cita.app.model.domain.entity.SaloonTag;
import tn.cita.app.model.domain.entity.Tag;
import tn.cita.app.model.dto.SaloonDto;
import tn.cita.app.model.dto.SaloonTagDto;
import tn.cita.app.model.dto.TagDto;

public interface SaloonTagMapper {
	
	public static SaloonTagDto map(@NotNull final SaloonTag saloonTag) {
		return SaloonTagDto.builder()
				.saloonId(saloonTag.getSaloonId())
				.tagId(saloonTag.getTagId())
				.taggedDate(saloonTag.getTaggedDate())
				.identifier(saloonTag.getIdentifier())
				.saloonDto(
					SaloonDto.builder()
						.id(saloonTag.getSaloon().getId())
						.identifier(saloonTag.getSaloon().getIdentifier())
						.code(saloonTag.getSaloon().getCode())
						.taxRef(saloonTag.getSaloon().getTaxRef())
						.name(saloonTag.getSaloon().getName())
						.isPrimary(saloonTag.getSaloon().getIsPrimary())
						.openingDate(saloonTag.getSaloon().getOpeningDate())
						.fullAdr(saloonTag.getSaloon().getFullAdr())
						.email(saloonTag.getSaloon().getEmail())
						.build())
				.tagDto(
					TagDto.builder()
						.id(saloonTag.getTag().getId())
						.identifier(saloonTag.getTag().getIdentifier())
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
				.identifier(saloonTagDto.getIdentifier())
				.saloon(
					Saloon.builder()
						.id(saloonTagDto.getSaloonDto().getId())
						.identifier(saloonTagDto.getSaloonDto().getIdentifier())
						.code(saloonTagDto.getSaloonDto().getCode())
						.taxRef(saloonTagDto.getSaloonDto().getTaxRef())
						.name(saloonTagDto.getSaloonDto().getName())
						.isPrimary(saloonTagDto.getSaloonDto().getIsPrimary())
						.openingDate(saloonTagDto.getSaloonDto().getOpeningDate())
						.fullAdr(saloonTagDto.getSaloonDto().getFullAdr())
						.email(saloonTagDto.getSaloonDto().getEmail())
						.build())
				.tag(
					Tag.builder()
						.id(saloonTagDto.getTagDto().getId())
						.identifier(saloonTagDto.getTagDto().getIdentifier())
						.name(saloonTagDto.getTagDto().getName())
						.description(saloonTagDto.getTagDto().getDescription())
						.build())
				.build();
	}
	
	
	
}














