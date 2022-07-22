package tn.cita.app.dto.request;

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

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import tn.cita.app.constant.AppConstants;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public final class OrderedDetailRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonProperty("reservationId")
	@NotNull(message = "Must belong to a specific reservation")
	private Integer reservationId;
	
	@JsonProperty("serviceDetailId")
	@NotNull(message = "Service must be specified")
	private Integer serviceDetailId;
	
	@JsonProperty("orderedDate")
	@JsonFormat(pattern = AppConstants.LOCAL_DATE_TIME_FORMAT, shape = Shape.STRING)
	@DateTimeFormat(pattern = AppConstants.LOCAL_DATE_TIME_FORMAT)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime orderedDate;
	
}









