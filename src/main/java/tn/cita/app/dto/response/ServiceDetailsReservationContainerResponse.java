package tn.cita.app.dto.response;

import java.io.Serializable;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import tn.cita.app.dto.OrderedDetailDto;
import tn.cita.app.dto.ServiceDetailDto;

@Builder
public record ServiceDetailsReservationContainerResponse(
		
		@JsonProperty("orderedDetails")
		Page<OrderedDetailDto> orderedDetailDtos,
		
		@JsonProperty("serviceDetails")
		Page<ServiceDetailDto> serviceDetailDtos) implements Serializable {}







