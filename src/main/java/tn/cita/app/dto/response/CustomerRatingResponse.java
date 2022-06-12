package tn.cita.app.dto.response;

import java.io.Serializable;

import org.springframework.data.domain.Page;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import tn.cita.app.dto.CustomerDto;
import tn.cita.app.dto.RatingDto;

@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
public final class CustomerRatingResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("customer")
	private final CustomerDto customerDto;
	
	@JsonInclude(value = Include.NON_NULL)
	@JsonProperty("ratings")
	private final Page<RatingDto> ratingDtos;
	
}














