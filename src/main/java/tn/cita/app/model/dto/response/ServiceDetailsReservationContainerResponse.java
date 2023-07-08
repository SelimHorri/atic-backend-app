package tn.cita.app.model.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;
import tn.cita.app.model.dto.OrderedDetailDto;
import tn.cita.app.model.dto.ServiceDetailDto;

public record ServiceDetailsReservationContainerResponse(
		@JsonProperty("serviceDetails") Page<ServiceDetailDto> serviceDetailDtos,
		@JsonProperty("orderedDetails") Page<OrderedDetailDto> orderedDetailDtos) {}



