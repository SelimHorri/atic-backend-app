package tn.cita.app.business.reservation.employee.worker.model;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Builder;

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


