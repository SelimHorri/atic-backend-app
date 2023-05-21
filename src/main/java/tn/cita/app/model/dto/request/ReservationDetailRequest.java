package tn.cita.app.model.dto.request;

import java.io.Serializable;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import lombok.Builder;

@Builder
public record ReservationDetailRequest(
		
		@NotNull(message = "Which reservation to update?")
		Integer reservationId,
		
		@Size(max = 255, message = "Input size must be less than {max} characters")
		String description) implements Serializable {}



