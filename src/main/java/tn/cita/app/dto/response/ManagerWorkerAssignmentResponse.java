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
import tn.cita.app.dto.TaskDto;

@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Builder
public final class ManagerWorkerAssignmentResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("manager")
	@JsonInclude(Include.NON_NULL)
	private final EmployeeDto managerDto;
	
	@JsonProperty("tasks")
	@JsonInclude(Include.NON_NULL)
	private final Page<TaskDto> tasksDtos;
	
}













