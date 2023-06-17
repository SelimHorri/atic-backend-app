package tn.cita.app.business.reservation.employee.manager.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

public record ReservationAssignWorkerRequest(
		Integer reservationId,
		
		@Size(min = 1, message = "You must assign at least {min} worker")
		@NotEmpty(message = "Workers must be assigned to the reservation")
		List<Integer> assignedWorkersIds,
		
		@Size(max = 255, message = "Manager description must not be over {max} characters")
		String managerDescription) implements Serializable {
	
	public ReservationAssignWorkerRequest {
		assignedWorkersIds = List.copyOf(assignedWorkersIds);
	}
	
}


