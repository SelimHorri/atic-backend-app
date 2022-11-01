package tn.cita.app.model.dto.response.actuator;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
@EqualsAndHashCode
public final class HealthActuatorResponse implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private String status;
	
}













