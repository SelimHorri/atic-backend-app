package tn.cita.app.mapper;

import javax.validation.constraints.NotNull;

import tn.cita.app.domain.entity.Tag;
import tn.cita.app.dto.TagDto;

public interface TagMapper {
	
	public static TagDto map(@NotNull Tag tag) {
		return TagDto.builder()
				.id(tag.getId())
				.name(tag.getName())
				.description(tag.getDescription())
				.build();
	}
	
	public static Tag map(@NotNull TagDto tagDto) {
		return Tag.builder()
				.id(tagDto.getId())
				.name(tagDto.getName())
				.description(tagDto.getDescription())
				.build();
	}
	
	
	
}













