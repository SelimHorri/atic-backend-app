package tn.cita.app.model.dto.request;

import java.io.Serializable;

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
public final class ReservationDetailRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@NotNull(message = "Which reservation to update?")
	private Integer reservationId;
	
	@Size(max = 255, message = "Input size must be less than {max} characters")
	private String description;
	
}












