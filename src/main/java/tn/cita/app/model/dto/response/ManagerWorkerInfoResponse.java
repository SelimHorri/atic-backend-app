package tn.cita.app.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;
import tn.cita.app.model.dto.EmployeeDto;

@JsonInclude(Include.NON_NULL)
public record ManagerWorkerInfoResponse(
		@JsonProperty("manager") EmployeeDto managerDto, 
		@JsonProperty("subWorkers") Page<EmployeeDto> subWorkersDtos) {}



