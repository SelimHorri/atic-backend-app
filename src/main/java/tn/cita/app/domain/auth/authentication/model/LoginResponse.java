package tn.cita.app.domain.auth.authentication.model;

import java.io.Serializable;

import lombok.Builder;

@Builder
public record LoginResponse(String username, String jwtToken) implements Serializable {}



