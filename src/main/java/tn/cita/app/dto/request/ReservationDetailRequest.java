package tn.cita.app.dto.request;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

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
	
	@NotNull(message = "Which reservation to update")
	private Integer reservationId;
	private String description;
	
}












