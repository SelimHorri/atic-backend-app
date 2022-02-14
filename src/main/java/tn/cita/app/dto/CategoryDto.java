package tn.cita.app.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true, exclude = {})
@SuperBuilder
public final class CategoryDto extends AbstractMappedDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Input should not be blank")
	private String name;
	
	@JsonInclude(Include.NON_NULL)
	private CategoryDto parentCategoryDto;
	
	@JsonIgnore
	private Set<CategoryDto> subCategoryDtos;
	
	@JsonInclude(Include.NON_NULL)
	private SaloonDto saloonDto;
	
	@JsonIgnore
	private Set<ServiceDetailDto> serviceDetailDtos;
	
}











