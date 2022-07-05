package tn.cita.app.dto.response;

import java.io.Serializable;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
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
@Builder
public final class ManagerReservationResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("manager")
	private final EmployeeDto managerDto;
	
	@JsonInclude(Include.NON_NULL)
	@JsonProperty("reservations")
	private final Page<ReservationDto> reservationDtos;
	
}
















