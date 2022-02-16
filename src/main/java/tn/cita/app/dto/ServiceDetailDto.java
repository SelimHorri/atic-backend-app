package tn.cita.app.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public final class ServiceDetailDto extends AbstractMappedDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Input name should not be blank")
	private String name;
	private String description;
	private Boolean isAvailable;
	private Double duration;
	private Double priceUnit;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("category")
	@NotNull(message = "Input category should not be null")
	private CategoryDto categoryDto;
	
	@JsonIgnore
	private Set<OrderedDetailDto> orderedDetailDtos;
	
}












