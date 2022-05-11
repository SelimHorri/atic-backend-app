package tn.cita.app.mapper;

import java.util.Optional;

import javax.validation.constraints.NotNull;

import tn.cita.app.domain.entity.Category;
import tn.cita.app.domain.entity.Saloon;
import tn.cita.app.dto.CategoryDto;

public interface CategoryMapper {
	
	public static CategoryDto map(@NotNull final Category category) {
		
		final var parentCategory = Optional
				.ofNullable(category.getParentCategory())
				.orElseGet(Category::new);
		
		return CategoryDto.builder()
				.id(category.getId())
				.name(category.getName())
				.parentCategoryId(parentCategory.getId())
				.saloonId(category.getSaloon().getId())
				.build();
	}
	
	public static Category map(@NotNull final CategoryDto categoryDto) {
		return Category.builder()
				.id(categoryDto.getId())
				.name(categoryDto.getName())
				.parentCategory(
					Category.builder()
						.id(categoryDto.getParentCategoryId())
						.build())
				.saloon(
					Saloon.builder()
						.id(categoryDto.getSaloonId())
						.build())
				.build();
	}
	
	
	
}














