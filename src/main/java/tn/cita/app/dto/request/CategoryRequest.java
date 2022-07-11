package tn.cita.app.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public final class CategoryRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer categoryId;
	
	@NotBlank(message = "Category name must not be blank")
	private String name;
	
	private Integer parentCategoryId;
	
	@NotNull(message = "Category must belong to your saloon")
	private Integer saloonId;
	
}










