package tn.cita.app.business.favourite.customer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;
import tn.cita.app.model.dto.CustomerDto;
import tn.cita.app.model.dto.FavouriteDto;

import java.io.Serializable;

public record CustomerFavouriteResponse(
		
		@JsonProperty("customer")
		CustomerDto customerDto,
		
		@JsonInclude(value = Include.NON_NULL)
		@JsonProperty("favourites")
		Page<FavouriteDto> favouriteDtos) implements Serializable {}


