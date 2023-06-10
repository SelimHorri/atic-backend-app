package tn.cita.app.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.springframework.data.domain.Page;
import tn.cita.app.model.dto.EmployeeDto;

import java.io.Serializable;

@Builder
public record ManagerWorkerInfoResponse(
		
		@JsonInclude(Include.NON_NULL)
		@JsonProperty("manager")
		EmployeeDto managerDto,
		
		@JsonInclude(Include.NON_NULL)
		@JsonProperty("subWorkers")
		Page<EmployeeDto> subWorkersDtos) implements Serializable {}



