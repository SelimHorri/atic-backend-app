package tn.cita.app.dto.response;

import java.io.Serializable;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import tn.cita.app.dto.OrderedDetailDto;
import tn.cita.app.dto.ServiceDetailDto;

@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Builder
public final class ServiceDetailsReservationContainerResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("orderedDetails")
	private final Page<OrderedDetailDto> orderedDetailDtos;
	
	@JsonProperty("serviceDetails")
	private final Page<ServiceDetailDto> serviceDetailDtos;
	
	// @JsonProperty("category")
	// private final CategoryDto categoryDto;
	
}














