package tn.cita.app.business.reservation.employee.worker.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;

import java.io.Serializable;

@Builder
public record TaskUpdateDescriptionRequest(
		
		@NotBlank(message = "Worker must be specified")
		String username,
		
		@NotNull(message = "Reservation must be specified")
		Integer reservationId,
		
		@Size(max = 255, message = "Comment must not be above {max} characters")
		String workerDescription,
		
		@Size(max = 255, message = "Comment must not be above {max} characters")
		String managerDescription) implements Serializable {}


