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
import tn.cita.app.dto.CredentialDto;
import tn.cita.app.dto.EmployeeDto;
import tn.cita.app.dto.TaskDto;

@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Builder
public final class WorkerProfileResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("worker")
	private final EmployeeDto workerDto;
	
	@JsonInclude(value = Include.NON_NULL)
	@JsonProperty("credential")
	private final CredentialDto credentialDto;
	
	@JsonInclude(value = Include.NON_NULL)
	@JsonProperty("tasks")
	private final Page<TaskDto> taskDtos;
	
}













