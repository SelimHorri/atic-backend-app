package tn.cita.app.dto;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Set;

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
import tn.cita.app.config.annotation.LocalDateTimeCustomFormat;
import tn.cita.app.domain.ReservationStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
@EqualsAndHashCode(callSuper = true, exclude = {})
@SuperBuilder
public final class ReservationDto extends AbstractMappedDto implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Input code should not be blank")
	private String code;
	private String description;
	
	@LocalDateTimeCustomFormat
	private LocalDateTime startDate;
	
	@LocalDateTimeCustomFormat
	private LocalDateTime cancelledDate;
	
	@NotNull(message = "Input reservationStatus should not be null")
	private ReservationStatus reservationStatus;
	
	@JsonInclude(Include.NON_NULL)
	@NotNull(message = "Input customer should not be null")
	private CustomerDto customerDto;
	
	@JsonIgnore
	private Set<OrderedDetailDto> orderedDetailDtos;
	
}














