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
import tn.cita.app.dto.CustomerDto;
import tn.cita.app.dto.OrderedDetailDto;
import tn.cita.app.dto.ReservationDto;
import tn.cita.app.dto.TaskDto;

@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Builder
public final class ReservationDetailResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("reservation")
	private final ReservationDto reservationDto;
	
	@JsonInclude(value = Include.NON_NULL)
	@JsonProperty("customer")
	private final CustomerDto customerDto;
	
	@JsonProperty("orderedDetails")
	private final Page<OrderedDetailDto> orderedDetailDtos;
	
	@JsonInclude(value = Include.NON_NULL)
	@JsonProperty("tasks")
	private final Page<TaskDto> taskDtos;
	
}







