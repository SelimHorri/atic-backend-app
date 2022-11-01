package tn.cita.app.model.dto.response;

import java.io.Serializable;

import lombok.Builder;
import tn.cita.app.model.dto.TaskDto;

@Builder
public record ReservationBeginEndTask(TaskDto taskBegin, TaskDto taskEnd) implements Serializable {}




