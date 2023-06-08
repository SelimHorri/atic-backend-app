package tn.cita.app.model.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@JsonInclude(Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
public final class TagDto extends AbstractMappedDto implements Serializable {
	
	private static final long serialVersionUID = -2808674187377290613L;
	
	@NotBlank(message = "Input name should not be blank")
	private String name;
	private String description;
	
}



