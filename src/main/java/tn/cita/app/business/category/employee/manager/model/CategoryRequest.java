package tn.cita.app.business.category.employee.manager.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;

public record CategoryRequest(
		
		Integer categoryId,
		
		@NotBlank(message = "Category name must not be blank")
		String name,
		
		Integer parentCategoryId,
		
		@NotNull(message = "Category must belong to your saloon")
		Integer saloonId) implements Serializable {}


