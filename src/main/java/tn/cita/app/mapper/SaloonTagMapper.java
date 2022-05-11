package tn.cita.app.mapper;

import javax.validation.constraints.NotNull;

import tn.cita.app.domain.entity.Saloon;
import tn.cita.app.domain.entity.SaloonTag;
import tn.cita.app.domain.entity.Tag;
import tn.cita.app.dto.SaloonTagDto;

public interface SaloonTagMapper {
	
	public static SaloonTagDto map(@NotNull final SaloonTag saloonTag) {
		return SaloonTagDto.builder()
				.saloonId(saloonTag.getSaloonId())
				.tagId(saloonTag.getTagId())
				.taggedDate(saloonTag.getTaggedDate())
				.saloonId(saloonTag.getSaloonId())
				.tagId(saloonTag.getTagId())
				.build();
	}
	
	public static SaloonTag map(@NotNull final SaloonTagDto saloonTagDto) {
		return SaloonTag.builder()
				.saloonId(saloonTagDto.getSaloonId())
				.tagId(saloonTagDto.getTagId())
				.taggedDate(saloonTagDto.getTaggedDate())
				.saloon(
					Saloon.builder()
						.id(saloonTagDto.getSaloonId())
						.build())
				.tag(
					Tag.builder()
						.id(saloonTagDto.getTagId())
						.build())
				.build();
	}
	
	
	
}














