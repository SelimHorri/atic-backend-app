package tn.cita.app.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import tn.cita.app.model.dto.OrderedDetailDto;
import tn.cita.app.model.dto.ReservationDto;
import tn.cita.app.model.dto.ServiceDetailDto;

public record OrderedDetailContainerResponse(
		
		@JsonProperty("orderedDetail")
		OrderedDetailDto orderedDetailDto,
		
		@JsonProperty("reservation")
		ReservationDto reservationDto,
		
		@JsonProperty("serviceDetail")
		ServiceDetailDto serviceDetailDto) {}



