package tn.cita.app.mapper;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import tn.cita.app.domain.entity.Category;
import tn.cita.app.domain.entity.Saloon;
import tn.cita.app.dto.CategoryDto;
import tn.cita.app.dto.SaloonDto;

public interface CategoryMapper {
	
	public static CategoryDto map(@NotNull final Category category) {
		
		final var parentCategory = Optional
				.ofNullable(category.getParentCategory())
				.orElseGet(Category::new);
		
		return CategoryDto.builder()
				.id(category.getId())
				.name(category.getName())
				.parentCategoryDto(
					CategoryDto.builder()
						.id(parentCategory.getId())
						.name(parentCategory.getName())
						.build())
				.saloonDto(
					SaloonDto.builder()
						.id(category.getSaloon().getId())
						.code(category.getSaloon().getCode())
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
				.name(categoryDto.getName())
				.parentCategory(
					Category.builder()
						.id(parentCategoryDto.getId())
						.name(parentCategoryDto.getName())
						.build())
				.saloon(
					Saloon.builder()
						.id(categoryDto.getSaloonDto().getId())
						.code(categoryDto.getSaloonDto().getCode())
						.name(categoryDto.getSaloonDto().getName())
						.isPrimary(categoryDto.getSaloonDto().getIsPrimary())
						.openingDate(categoryDto.getSaloonDto().getOpeningDate())
						.fullAdr(categoryDto.getSaloonDto().getFullAdr())
						.email(categoryDto.getSaloonDto().getEmail())
						.build())
				.build();
	}
	
	
	
}














