package tn.cita.app.model.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import tn.cita.app.model.dto.OrderedDetailDto;
import tn.cita.app.model.dto.ReservationDto;
import tn.cita.app.model.dto.ServiceDetailDto;

@Builder
public record OrderedDetailContainerResponse(
		
		@JsonProperty("orderedDetail")
		OrderedDetailDto orderedDetailDto,
		
		@JsonProperty("reservation")
		ReservationDto reservationDto,
		
		@JsonProperty("serviceDetail")
		ServiceDetailDto serviceDetailDto) implements Serializable {}





