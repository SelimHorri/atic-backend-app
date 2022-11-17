package tn.cita.app.model.dto.response.api;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;

import lombok.Builder;

@Builder
public record ApiResponse<T>(
		
		@JsonFormat(shape = Shape.STRING)
		@JsonSerialize(using = InstantSerializer.class)
		@JsonDeserialize(using = InstantDeserializer.class)
		Instant timestamp,
		Integer totalResult,
		HttpStatus httpStatus,
		Boolean acknowledge,
		T responseBody) implements Serializable {
	
	/*
	 * Important: responseBody MUST be an immutable object!
	 * for List: we may use Java9+ factory methods of List interface: 
	 * for example: List.copyOf(responseBody)
	 */
	public ApiResponse(final Integer totalResult, final HttpStatus httpStatus, final Boolean acknowledge, final T responseBody) {
		this(Instant.now(), totalResult, httpStatus, acknowledge, responseBody);
	}
	
	
	
}










