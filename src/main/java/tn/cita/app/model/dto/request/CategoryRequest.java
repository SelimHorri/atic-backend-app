package tn.cita.app.model.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Builder;

@Builder
public record CategoryRequest(
		
		Integer categoryId,
		
		@NotBlank(message = "Category name must not be blank")
		String name,
		
		Integer parentCategoryId,
		
		@NotNull(message = "Category must belong to your saloon")
		Integer saloonId) implements Serializable {}



