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
public final class ServiceDetailDto extends AbstractMappedDto implements Serializable {
	
	private static final long serialVersionUID = -4963632929810972853L;
	
	@NotBlank(message = "Input name should not be blank")
	private String name;
	private String description;
	private Boolean isAvailable;
	private Double duration;
	private Double priceUnit;
	
	@JsonProperty("category")
	private CategoryDto categoryDto;
	
}



