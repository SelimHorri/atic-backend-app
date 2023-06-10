package tn.cita.app.model.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import org.springframework.format.annotation.DateTimeFormat;
import tn.cita.app.constant.AppConstants;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Builder
public record ReservationRequest(
		
		@NotBlank(message = "Customer username must be specified to perform a reservation")
		String username,
		
		@NotNull(message = "Reservation must belong to a specific Saloon")
		Integer saloonId,
		
		@NotNull(message = "Specify a date to book a reservation")
		@JsonFormat(pattern = AppConstants.LOCAL_DATE_TIME_FORMAT, shape = Shape.STRING)
		@DateTimeFormat(pattern = AppConstants.LOCAL_DATE_TIME_FORMAT)
		@JsonSerialize(using = LocalDateTimeSerializer.class)
		@JsonDeserialize(using = LocalDateTimeDeserializer.class)
		LocalDateTime startDate,
		
		@NotNull(message = "Input must not be null")
		@Size(min = 1, message = "You must specify {min} service at least")
		List<Integer> serviceDetailsIds,
		
		String description) implements Serializable {
	
	public ReservationRequest {
		serviceDetailsIds = List.copyOf(serviceDetailsIds);
	}
	
}



