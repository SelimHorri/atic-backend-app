package tn.cita.app.dto.response;

import java.io.Serializable;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import tn.cita.app.dto.CustomerDto;
import tn.cita.app.dto.FavouriteDto;

@Builder
public record CustomerFavouriteResponse(
		
		@JsonProperty("customer") 
		CustomerDto customerDto,
		
		@JsonInclude(value = Include.NON_NULL) 
		@JsonProperty("favourites") 
		Page<FavouriteDto> favouriteDtos
		
	) implements Serializable {}














