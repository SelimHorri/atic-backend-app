package tn.cita.app.dto.response;

import java.io.Serializable;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import tn.cita.app.dto.EmployeeDto;
import tn.cita.app.dto.ReservationDto;

@Builder
public record ManagerReservationResponse(
		
		@JsonInclude(Include.NON_NULL)
		@JsonProperty("manager")
		EmployeeDto managerDto,
		
		@JsonInclude(Include.NON_NULL)
		@JsonProperty("reservations")
		Page<ReservationDto> reservationDtos
		
	) implements Serializable {}
















