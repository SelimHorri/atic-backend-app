package tn.cita.app.model.dto.response.actuator;

import java.io.Serializable;

public record HealthActuatorResponse(String status, String[] groups) implements Serializable {}



