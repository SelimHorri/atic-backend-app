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
public final class ServiceDetailRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// @NotNull(message = "Service must be specified")
	private Integer serviceDetailId;
	
	@NotBlank(message = "Service name must be not blank")
	private String name;
	
	@NotNull(message = "Is Service available ?")
	private Boolean isAvailable;
	
	@NotNull(message = "Service duration (min) must be specified approximately")
	private Double duration;
	
	@NotNull(message = "Service price unit (DT) must be specified")
	private Double priceUnit;
	private String description;
	
	@NotNull(message = "Service must belong to a specific category")
	private Integer categoryId;
	
}














