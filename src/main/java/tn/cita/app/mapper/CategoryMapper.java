package tn.cita.app.mapper;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import tn.cita.app.model.domain.entity.Category;
import tn.cita.app.model.domain.entity.Saloon;
import tn.cita.app.model.dto.CategoryDto;
import tn.cita.app.model.dto.SaloonDto;

public interface CategoryMapper {
	
public static CategoryDto map(@NotNull final Category category) {
		
		final var parentCategory = Optional
				.ofNullable(category.getParentCategory())
				.orElseGet(Category::new);
		
		return CategoryDto.builder()
				.id(category.getId())
				.identifier(category.getIdentifier())
				.name(category.getName())
				.parentCategoryDto(
					CategoryDto.builder()
						.id(parentCategory.getId())
						.identifier(parentCategory.getIdentifier())
						.name(parentCategory.getName())
						.build())
				.saloonDto(
					SaloonDto.builder()
						.id(category.getSaloon().getId())
						.identifier(category.getSaloon().getIdentifier())
						.code(category.getSaloon().getCode())
						.taxRef(category.getSaloon().getTaxRef())
						.name(category.getSaloon().getName())
						.isPrimary(category.getSaloon().getIsPrimary())
						.openingDate(category.getSaloon().getOpeningDate())
						.fullAdr(category.getSaloon().getFullAdr())
						.email(category.getSaloon().getEmail())
						.build())
				.build();
	}
	
	public static Category map(@NotNull final CategoryDto categoryDto) {
		
		final var parentCategoryDto = Optional
				.ofNullable(categoryDto.getParentCategoryDto())
				.orElseGet(CategoryDto::new);
		
		return Category.builder()
				.id(categoryDto.getId())
				.identifier(categoryDto.getIdentifier())
				.name(categoryDto.getName())
				.parentCategory(
					Category.builder()
						.id(parentCategoryDto.getId())
						.identifier(parentCategoryDto.getIdentifier())
						.name(parentCategoryDto.getName())
						.build())
				.saloon(
					Saloon.builder()
						.id(categoryDto.getSaloonDto().getId())
						.identifier(categoryDto.getSaloonDto().getIdentifier())
						.code(categoryDto.getSaloonDto().getCode())
						.taxRef(categoryDto.getSaloonDto().getTaxRef())
						.name(categoryDto.getSaloonDto().getName())
						.isPrimary(categoryDto.getSaloonDto().getIsPrimary())
						.openingDate(categoryDto.getSaloonDto().getOpeningDate())
						.fullAdr(categoryDto.getSaloonDto().getFullAdr())
						.email(categoryDto.getSaloonDto().getEmail())
						.build())
				.build();
	}
	
	
	
}














