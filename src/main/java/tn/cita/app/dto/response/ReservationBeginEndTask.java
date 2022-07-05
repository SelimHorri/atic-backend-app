package tn.cita.app.dto.response;

import java.io.Serializable;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import tn.cita.app.dto.TaskDto;

@RequiredArgsConstructor
@ToString
@EqualsAndHashCode
@Getter
@Builder
public final class ReservationBeginEndTask implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private final TaskDto taskBegin;
	private final TaskDto taskEnd;
	
}














