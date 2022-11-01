package tn.cita.app.mapper;

import javax.validation.constraints.NotNull;

import tn.cita.app.model.domain.entity.Category;
import tn.cita.app.model.domain.entity.ServiceDetail;
import tn.cita.app.model.dto.CategoryDto;
import tn.cita.app.model.dto.ServiceDetailDto;

public interface ServiceDetailMapper {
	
	public static ServiceDetailDto map(@NotNull final ServiceDetail serviceDetail) {
		return ServiceDetailDto.builder()
				.id(serviceDetail.getId())
				.identifier(serviceDetail.getIdentifier())
				.name(serviceDetail.getName())
				.description(serviceDetail.getDescription())
				.isAvailable(serviceDetail.getIsAvailable())
				.duration(serviceDetail.getDuration())
				.priceUnit(serviceDetail.getPriceUnit())
				.categoryDto(
					CategoryDto.builder()
						.id(serviceDetail.getCategory().getId())
						.identifier(serviceDetail.getCategory().getIdentifier())
						.name(serviceDetail.getCategory().getName())
						.build())
				.build();
	}
	
	public static ServiceDetail map(@NotNull final ServiceDetailDto serviceDetailDto) {
		return ServiceDetail.builder()
				.id(serviceDetailDto.getId())
				.identifier(serviceDetailDto.getIdentifier())
				.name(serviceDetailDto.getName())
				.description(serviceDetailDto.getDescription())
				.isAvailable(serviceDetailDto.getIsAvailable())
				.duration(serviceDetailDto.getDuration())
				.priceUnit(serviceDetailDto.getPriceUnit())
				.category(
					Category.builder()
						.id(serviceDetailDto.getCategoryDto().getId())
						.identifier(serviceDetailDto.getCategoryDto().getIdentifier())
						.name(serviceDetailDto.getCategoryDto().getName())
						.build())
				.build();
	}
	
	
	
}














