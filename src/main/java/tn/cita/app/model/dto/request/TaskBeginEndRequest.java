package tn.cita.app.model.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;

@Builder
public record TaskBeginEndRequest(
		
		@NotBlank(message = "User must be specified")
		String username,
		
		@NotNull(message = "Reservation must be specified")
		Integer reservationId,
		
		@Size(max = 255, message = "Comment must not be above {max} characters")
		String workerDescription) implements Serializable {}







