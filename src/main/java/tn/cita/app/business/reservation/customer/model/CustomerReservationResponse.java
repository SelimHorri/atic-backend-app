package tn.cita.app.business.reservation.customer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.springframework.data.domain.Page;
import tn.cita.app.model.dto.CustomerDto;
import tn.cita.app.model.dto.ReservationDto;

import java.io.Serializable;

@Builder
public record CustomerReservationResponse(
		
		@JsonProperty("customer") 
		CustomerDto customerDto,
		
		@JsonInclude(value = Include.NON_NULL) 
		@JsonProperty("reservations") 
		Page<ReservationDto> reservationDtos) implements Serializable {}



