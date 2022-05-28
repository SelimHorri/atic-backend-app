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
import tn.cita.app.dto.CredentialDto;
import tn.cita.app.dto.CustomerDto;
import tn.cita.app.dto.FavouriteDto;
import tn.cita.app.dto.RatingDto;
import tn.cita.app.dto.ReservationDto;

@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Builder
public final class CustomerProfileResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("customer")
	private final CustomerDto customerDto;
	
	@JsonInclude(value = Include.NON_NULL)
	@JsonProperty("credential")
	private final CredentialDto credentialDto;
	
	@JsonInclude(value = Include.NON_NULL)
	@JsonProperty("reservations")
	private final Page<ReservationDto> reservationDtos;
	
	@JsonInclude(value = Include.NON_NULL)
	@JsonProperty("favourites")
	private final Page<FavouriteDto> favouriteDtos;
	
	@JsonInclude(value = Include.NON_NULL)
	@JsonProperty("ratings")
	private final Page<RatingDto> ratingDtos;
	
}













