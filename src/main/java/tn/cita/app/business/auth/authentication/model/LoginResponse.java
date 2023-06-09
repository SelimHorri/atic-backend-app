package tn.cita.app.business.auth.authentication.model;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record LoginResponse(String username, String jwtToken) implements Serializable {}



