package tn.cita.app.model.dto.request;

import java.io.Serializable;
import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public final class ReservationAssignWorkerRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer reservationId;
	
	@Size(min = 1, message = "You must assign at least {min} worker")
	@NotEmpty(message = "Workers must be assigned to the reservation")
	private List<Integer> assignedWorkersIds;
	
	@Size(max = 255, message = "Manager description must not be over {max} characters")
	private String managerDescription;
	
}














