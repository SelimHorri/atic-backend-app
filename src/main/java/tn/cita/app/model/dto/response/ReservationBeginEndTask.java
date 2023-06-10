package tn.cita.app.model.dto.response;

import lombok.Builder;
import tn.cita.app.model.dto.TaskDto;

import java.io.Serializable;

@Builder
public record ReservationBeginEndTask(TaskDto taskBegin, TaskDto taskEnd) implements Serializable {}


