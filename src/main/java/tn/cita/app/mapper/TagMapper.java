package tn.cita.app.mapper;

import lombok.NonNull;
import tn.cita.app.model.domain.entity.Tag;
import tn.cita.app.model.dto.TagDto;

public interface TagMapper {
	
	public static TagDto toDto(@NonNull Tag tag) {
		return TagDto.builder()
				.id(tag.getId())
				.identifier(tag.getIdentifier())
				.name(tag.getName())
				.description(tag.getDescription())
				.build();
	}
	
}



