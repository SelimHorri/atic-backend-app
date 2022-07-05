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
import tn.cita.app.dto.CategoryDto;
import tn.cita.app.dto.EmployeeDto;
import tn.cita.app.dto.ReservationDto;
import tn.cita.app.dto.SaloonTagDto;
import tn.cita.app.dto.ServiceDetailDto;

@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Builder
public final class ManagerProfileResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("manager")
	private final EmployeeDto managerDto;
	
	@JsonInclude(value = Include.NON_NULL)
	@JsonProperty("workers")
	private final Page<EmployeeDto> workerDtos;
	
	@JsonInclude(value = Include.NON_NULL)
	@JsonProperty("reservations")
	private final Page<ReservationDto> reservationDtos;
	
	@JsonInclude(value = Include.NON_NULL)
	@JsonProperty("saloonTags")
	private final Page<SaloonTagDto> saloonTagDtos;
	
	@JsonInclude(value = Include.NON_NULL)
	@JsonProperty("categories")
	private final Page<CategoryDto> categoryDtos;
	
	@JsonInclude(value = Include.NON_NULL)
	@JsonProperty("serviceDetails")
	private final Page<ServiceDetailDto> serviceDetailDtos;
	
}
















