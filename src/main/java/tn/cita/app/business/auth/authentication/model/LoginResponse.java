package tn.cita.app.business.auth.authentication.model;

import java.io.Serializable;

import lombok.Builder;

@Builder
public record LoginResponse(String username, String jwtToken) implements Serializable {}



