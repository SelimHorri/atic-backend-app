package tn.cita.app.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tn.cita.app.config.annotation.LocalDateTimeCustomFormat;
import tn.cita.app.domain.UserRating;

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public final class RatingDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Input employeeId should not be null")
	private Integer employeeId;
	
	@NotNull(message = "Input customerId should not be null")
	private Integer customerId;
	
	@LocalDateTimeCustomFormat
	private LocalDateTime rateDate;
	
	@NotNull(message = "Input rate should not be null")
	private UserRating rate;
	private String description;
	
	@JsonInclude(Include.NON_NULL)
	@NotNull(message = "Input employee should not be null")
	private EmployeeDto employeeDto;
	
	@JsonInclude(Include.NON_NULL)
	@NotNull(message = "Input customer should not be null")
	private CustomerDto customerDto;
	
}










