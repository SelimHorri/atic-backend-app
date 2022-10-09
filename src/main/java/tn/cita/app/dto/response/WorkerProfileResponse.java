package tn.cita.app.dto.response;

import java.io.Serializable;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import tn.cita.app.dto.CredentialDto;
import tn.cita.app.dto.EmployeeDto;
import tn.cita.app.dto.TaskDto;

@Builder
public record WorkerProfileResponse(
		
		@JsonProperty("worker")
		EmployeeDto workerDto,
		
		@JsonInclude(value = Include.NON_NULL)
		@JsonProperty("credential")
		CredentialDto credentialDto,
		
		@JsonInclude(value = Include.NON_NULL)
		@JsonProperty("tasks")
		Page<TaskDto> taskDtos
		
	) implements Serializable {}













