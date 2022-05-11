package tn.cita.app.dto.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import tn.cita.app.dto.OrderedDetailDto;
import tn.cita.app.dto.ReservationDto;
import tn.cita.app.dto.ServiceDetailDto;

@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Builder
public final class OrderedDetailContainerResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("orderedDetail")
	private final OrderedDetailDto orderedDetailDto;
	
	@JsonProperty("reservation")
	private final ReservationDto reservationDto;
	
	@JsonProperty("serviceDetail")
	private final ServiceDetailDto serviceDetailDto;
	
}














