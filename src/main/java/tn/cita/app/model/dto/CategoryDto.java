package tn.cita.app.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public final class CategoryDto extends AbstractMappedDto implements Serializable {
	
	private static final long serialVersionUID = 4600520993749635081L;
	
	@NotBlank(message = "Input should not be blank")
	private String name;
	
	@JsonProperty("parentCategory")
	private CategoryDto parentCategoryDto;
	
	@JsonProperty("saloon")
	private SaloonDto saloonDto;
	
}



