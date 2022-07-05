package tn.cita.app.dto.response;

import java.io.Serializable;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import tn.cita.app.dto.EmployeeDto;
import tn.cita.app.dto.ReservationDto;

@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
public final class ReservationSubWorkerResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonInclude(value = Include.NON_NULL)
	@JsonProperty("reservation")
	private final ReservationDto reservationDto;
	
	@JsonInclude(value = Include.NON_NULL)
	@JsonProperty("subWorkers")
	private final Page<EmployeeDto> subWorkerDtos;
	
}
















