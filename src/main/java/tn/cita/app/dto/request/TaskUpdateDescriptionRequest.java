package tn.cita.app.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class TaskUpdateDescriptionRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotBlank(message = "Worker must be specified")
	private String username;
	
	@NotNull(message = "Reservation must be specified")
	private Integer reservationId;
	
	@Size(max = 255, message = "Comment must not be above {max} characters")
	private String workerDescription;
	
	@Size(max = 255, message = "Comment must not be above {max} characters")
	private String managerDescription;
	
}












