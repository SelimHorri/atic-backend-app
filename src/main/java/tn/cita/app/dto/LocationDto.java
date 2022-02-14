package tn.cita.app.dto;

import java.io.Serializable;
import java.util.Set;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

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
public final class LocationDto extends AbstractMappedDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Input zipcode should not be blank")
	private String zipcode;
	
	@NotBlank(message = "Input city should not be blank")
	private String city;
	
	@NotBlank(message = "Input state should not be blank")
	private String state;
	
	@JsonIgnore
	private Set<SaloonDto> saloonDtos;
	
}













