package tn.cita.app.dto.response;

import java.io.Serializable;

import lombok.Builder;

@Builder
public record LoginResponse(String username, String jwtToken) implements Serializable {}





