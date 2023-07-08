package tn.cita.app.business.rating.customer.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.domain.Page;
import tn.cita.app.model.dto.CustomerDto;
import tn.cita.app.model.dto.RatingDto;

public record CustomerRatingResponse(
		
		@JsonProperty("customer")
		CustomerDto customerDto,
		
		@JsonInclude(value = Include.NON_NULL)
		@JsonProperty("ratings")
		Page<RatingDto> ratingDtos) {}



