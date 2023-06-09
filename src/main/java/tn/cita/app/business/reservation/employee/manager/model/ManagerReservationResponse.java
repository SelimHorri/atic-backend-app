package tn.cita.app.business.reservation.employee.manager.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.springframework.data.domain.Page;
import tn.cita.app.model.dto.EmployeeDto;
import tn.cita.app.model.dto.ReservationDto;

import java.io.Serializable;

@Builder
public record ManagerReservationResponse(
		
		@JsonInclude(Include.NON_NULL)
		@JsonProperty("manager")
		EmployeeDto managerDto,
		
		@JsonInclude(Include.NON_NULL)
		@JsonProperty("reservations")
		Page<ReservationDto> reservationDtos) implements Serializable {}


