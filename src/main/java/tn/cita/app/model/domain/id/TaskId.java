package tn.cita.app.model.domain.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class TaskId implements Serializable {
	
	private static final long serialVersionUID = -5033050639136364710L;
	private Integer workerId;
	private Integer reservationId;
	
}



