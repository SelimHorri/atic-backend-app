package tn.cita.app.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import tn.cita.app.constant.AppConstant;
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
	
	@JsonFormat(pattern = AppConstant.LOCAL_DATE_TIME_FORMAT, shape = Shape.STRING)
	@DateTimeFormat(pattern = AppConstant.LOCAL_DATE_TIME_FORMAT)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime rateDate;
	
	@NotNull(message = "Input rate should not be null")
	private UserRating rate;
	private String description;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("employee")
	@NotNull(message = "Input employee should not be null")
	private EmployeeDto employeeDto;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("customer")
	@NotNull(message = "Input customer should not be null")
	private CustomerDto customerDto;
	
}











