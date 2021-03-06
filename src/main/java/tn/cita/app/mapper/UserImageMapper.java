package tn.cita.app.mapper;

import javax.validation.constraints.NotNull;

import tn.cita.app.domain.entity.UserImage;
import tn.cita.app.dto.UserImageDto;

public interface UserImageMapper {
	
	public static UserImageDto map(@NotNull final UserImage userImage) {
		return UserImageDto.builder()
				.id(userImage.getId())
				.name(userImage.getName())
				.type(userImage.getType())
				.size(userImage.getSize())
				.imageLob(userImage.getImageLob())
				.build();
	}
	
	public static UserImage map(@NotNull final UserImageDto userImageDto) {
		return UserImage.builder()
				.id(userImageDto.getId())
				.name(userImageDto.getName())
				.type(userImageDto.getType())
				.size(userImageDto.getSize())
				.imageLob(userImageDto.getImageLob())
				.build();
	}
	
	
	
}













