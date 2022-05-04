package tn.cita.app.dto;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;

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
	private Integer categoryId;
	
}













