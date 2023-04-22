package tn.cita.app.business.reservation.employee.manager.model;

import java.io.Serializable;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import tn.cita.app.model.dto.EmployeeDto;
import tn.cita.app.model.dto.ReservationDto;

@Builder
public record ReservationSubWorkerResponse(
		
		@JsonInclude(value = Include.NON_NULL)
		@JsonProperty("reservation")
		ReservationDto reservationDto,
		
		@JsonInclude(value = Include.NON_NULL)
		@JsonProperty("subWorkers")
		Page<EmployeeDto> subWorkerDtos) implements Serializable {}


