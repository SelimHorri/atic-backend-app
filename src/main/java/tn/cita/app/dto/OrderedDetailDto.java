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

@NoArgsConstructor
@AllArgsConstructor
@Data
@SuperBuilder
public final class OrderedDetailDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Input reservationId should not be null")
	private Integer reservationId;
	
	@NotNull(message = "Input serviceDetailId should not be null")
	private Integer serviceDetailId;
	
	@LocalDateTimeCustomFormat
	private LocalDateTime orderedDate;
	
	@JsonInclude(Include.NON_NULL)
	@NotNull(message = "Input reservation should not be null")
	private ReservationDto reservationDto;
	
	@JsonInclude(Include.NON_NULL)
	@NotNull(message = "Input serviceDetail should not be null")
	private ServiceDetailDto serviceDetailDto;
	
}












