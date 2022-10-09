package tn.cita.app.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import tn.cita.app.dto.OrderedDetailDto;
import tn.cita.app.dto.ReservationDto;
import tn.cita.app.dto.ServiceDetailDto;

@Builder
public record OrderedDetailContainerResponse(
		
		@JsonProperty("orderedDetail")
		OrderedDetailDto orderedDetailDto,
		
		@JsonProperty("reservation")
		ReservationDto reservationDto,
		
		@JsonProperty("serviceDetail")
		ServiceDetailDto serviceDetailDto) implements Serializable {}





