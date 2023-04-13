package tn.cita.app.domain.profile.customer.model;

import java.io.Serializable;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import tn.cita.app.model.dto.CredentialDto;
import tn.cita.app.model.dto.CustomerDto;
import tn.cita.app.model.dto.FavouriteDto;
import tn.cita.app.model.dto.RatingDto;
import tn.cita.app.model.dto.ReservationDto;

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



