package tn.cita.app.business.profile.customer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import org.springframework.data.domain.Page;
import tn.cita.app.model.dto.*;

import java.io.Serializable;

@Builder
public record CustomerProfileResponse(
		
		@JsonProperty("customer")
		CustomerDto customerDto,
		
		@JsonInclude(value = Include.NON_NULL)
		@JsonProperty("credential")
		CredentialDto credentialDto,
		
		@JsonInclude(value = Include.NON_NULL)
		@JsonProperty("reservations")
		Page<ReservationDto> reservationDtos,
		
		@JsonInclude(value = Include.NON_NULL)
		@JsonProperty("favourites")
		Page<FavouriteDto> favouriteDtos,
		
		@JsonInclude(value = Include.NON_NULL)
		@JsonProperty("ratings")
		Page<RatingDto> ratingDtos) implements Serializable {}



