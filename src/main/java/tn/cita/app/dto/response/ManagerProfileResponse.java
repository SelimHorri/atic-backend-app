package tn.cita.app.dto.response;

import java.io.Serializable;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import tn.cita.app.dto.CategoryDto;
import tn.cita.app.dto.EmployeeDto;
import tn.cita.app.dto.ReservationDto;
import tn.cita.app.dto.SaloonTagDto;
import tn.cita.app.dto.ServiceDetailDto;

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
		Page<ServiceDetailDto> serviceDetailDtos
		
	) implements Serializable {}
















