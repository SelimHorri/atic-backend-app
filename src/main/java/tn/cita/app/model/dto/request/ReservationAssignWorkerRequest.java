package tn.cita.app.model.dto.request;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.Builder;

@Builder
public record ReservationAssignWorkerRequest(
		
		Integer reservationId,
		
		@Size(min = 1, message = "You must assign at least {min} worker")
		@NotEmpty(message = "Workers must be assigned to the reservation")
		List<Integer> assignedWorkersIds,
		
		@Size(max = 255, message = "Manager description must not be over {max} characters")
		String managerDescription) implements Serializable {}


