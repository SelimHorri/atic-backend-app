package tn.cita.app.domain.id;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskId implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Integer workerId;
	private Integer reservationId;
	
}










