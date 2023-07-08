package tn.cita.app.business.reservation.employee.manager.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;
import tn.cita.app.model.dto.EmployeeDto;
import tn.cita.app.model.dto.ReservationDto;

@JsonInclude(value = Include.NON_NULL)
public record ReservationSubWorkerResponse(
		@JsonProperty("reservation") ReservationDto reservationDto,
		@JsonProperty("subWorkers") Page<EmployeeDto> subWorkerDtos) {}


