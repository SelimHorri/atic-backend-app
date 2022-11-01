package tn.cita.app.mapper;

import javax.validation.constraints.NotNull;

import tn.cita.app.model.domain.entity.Tag;
import tn.cita.app.model.dto.TagDto;

public interface TagMapper {
	
	public static TagDto map(@NotNull Tag tag) {
		return TagDto.builder()
				.id(tag.getId())
				.identifier(tag.getIdentifier())
				.name(tag.getName())
				.description(tag.getDescription())
				.build();
	}
	
	public static Tag map(@NotNull TagDto tagDto) {
		return Tag.builder()
				.id(tagDto.getId())
				.identifier(tagDto.getIdentifier())
				.name(tagDto.getName())
				.description(tagDto.getDescription())
				.build();
	}
	
	
	
}













