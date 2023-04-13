package tn.cita.app.domain.servicedetails.employee.manager.model;

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
	
	private static final long serialVersionUID = 7288108275012703210L;
	
	private Integer serviceDetailId;
	
	@NotBlank(message = "Service name must be not blank")
	private String name;
	
	@Builder.Default
	private Boolean isAvailable = true;
	
	@NotNull(message = "Service duration (min) must be specified approximately")
	private Double duration;
	
	@NotNull(message = "Service price unit (DT) must be specified")
	private Double priceUnit;
	private String description;
	
	@NotNull(message = "Service must belong to a specific category")
	private Integer categoryId;
	
}





