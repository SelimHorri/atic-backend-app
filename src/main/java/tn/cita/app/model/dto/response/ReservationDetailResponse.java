package tn.cita.app.model.dto.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.springframework.data.domain.Page;
import tn.cita.app.model.dto.CustomerDto;
import tn.cita.app.model.dto.OrderedDetailDto;
import tn.cita.app.model.dto.ReservationDto;
import tn.cita.app.model.dto.TaskDto;

import java.io.Serializable;

@Builder
public record ReservationDetailResponse(
		
		@JsonProperty("reservation")
		ReservationDto reservationDto,
		
		@JsonInclude(value = Include.NON_NULL)
		@JsonProperty("customer")
		CustomerDto customerDto,
		
		@JsonProperty("orderedDetails")
		Page<OrderedDetailDto> orderedDetailDtos,
		
		@JsonInclude(value = Include.NON_NULL)
		@JsonProperty("tasks")
		Page<TaskDto> taskDtos) implements Serializable {}


