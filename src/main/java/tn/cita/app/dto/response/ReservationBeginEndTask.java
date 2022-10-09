package tn.cita.app.dto.response;

import java.io.Serializable;

import lombok.Builder;
import tn.cita.app.dto.TaskDto;

@Builder
public record ReservationBeginEndTask(TaskDto taskBegin, TaskDto taskEnd) implements Serializable {}




