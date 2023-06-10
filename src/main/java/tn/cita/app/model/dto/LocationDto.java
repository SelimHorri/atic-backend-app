package tn.cita.app.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
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
public final class LocationDto extends AbstractMappedDto implements Serializable {
	
	private static final long serialVersionUID = 5424410748327656191L;
	
	@NotBlank(message = "Input zipcode should not be blank")
	private String zipcode;
	
	@NotBlank(message = "Input city should not be blank")
	private String city;
	
	@NotBlank(message = "Input state should not be blank")
	private String state;
	
}




