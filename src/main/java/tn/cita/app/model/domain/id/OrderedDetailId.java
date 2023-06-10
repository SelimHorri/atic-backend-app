package tn.cita.app.model.domain.id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class OrderedDetailId implements Serializable {
	
	private static final long serialVersionUID = 2151289755647603515L;
	private Integer reservationId;
	private Integer serviceDetailId;
	
}



