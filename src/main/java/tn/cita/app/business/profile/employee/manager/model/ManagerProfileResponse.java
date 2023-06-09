package tn.cita.app.business.profile.employee.manager.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.springframework.data.domain.Page;
import tn.cita.app.model.dto.*;

import java.io.Serializable;

@Builder
public record ManagerProfileResponse(
		
		@JsonProperty("manager")
		EmployeeDto managerDto,
		
		@JsonInclude(value = Include.NON_NULL)
		@JsonProperty("workers")
		Page<EmployeeDto> workerDtos,
		
		@JsonInclude(value = Include.NON_NULL)
		@JsonProperty("reservations")
		Page<ReservationDto> reservationDtos,
		
		@JsonInclude(value = Include.NON_NULL)
		@JsonProperty("saloonTags")
		Page<SaloonTagDto> saloonTagDtos,
		
		@JsonInclude(value = Include.NON_NULL)
		@JsonProperty("categories")
		Page<CategoryDto> categoryDtos,
		
		@JsonInclude(value = Include.NON_NULL)
		@JsonProperty("serviceDetails")
		Page<ServiceDetailDto> serviceDetailDtos) implements Serializable {}


