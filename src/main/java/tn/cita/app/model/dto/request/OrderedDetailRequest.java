package tn.cita.app.model.dto.request;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Builder;
import tn.cita.app.constant.AppConstants;

@Builder
public record OrderedDetailRequest(
		
		@JsonProperty("reservationId")
		@NotNull(message = "Must belong to a specific reservation")
		Integer reservationId,
		
		@JsonProperty("serviceDetailId")
		@NotNull(message = "Service must be specified")
		Integer serviceDetailId,
		
		@JsonProperty("orderedDate")
		@JsonFormat(pattern = AppConstants.LOCAL_DATE_TIME_FORMAT, shape = Shape.STRING)
		@DateTimeFormat(pattern = AppConstants.LOCAL_DATE_TIME_FORMAT)
		@JsonSerialize(using = LocalDateTimeSerializer.class)
		@JsonDeserialize(using = LocalDateTimeDeserializer.class)
		LocalDateTime orderedDate) implements Serializable {}


