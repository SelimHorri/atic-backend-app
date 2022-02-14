package tn.cita.app.dto;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Set;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tn.cita.app.config.annotation.LocalDateCustomFormat;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true, exclude = {})
@SuperBuilder
public final class SaloonDto extends AbstractMappedDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Input code should not be blank")
	private String code;
	
	@NotBlank(message = "Input name should not be blank")
	private String name;
	private Boolean isPrimary;
	
	@LocalDateCustomFormat
	private LocalDate openingDate;
	private String fullAdr;
	
	@Email(message = "Input email should be in email format")
	@NotBlank(message = "Input email should not be blank")
	private String email;
	
	@JsonInclude(Include.NON_NULL)
	@NotNull(message = "Input location should not be null")
	private LocationDto locationDto;
	
	@JsonIgnore
	private Set<EmployeeDto> employeeDtos;
	
	@JsonIgnore
	private Set<FavouriteDto> favouriteDtos;
	
	@JsonIgnore
	private Set<SaloonImageDto> saloonImageDtos;
	
	@JsonIgnore
	private Set<SaloonTagDto> saloonTagDtos;
	
	@JsonIgnore
	private Set<CategoryDto> categoryDtos;
	
}












