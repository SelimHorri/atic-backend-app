package tn.cita.app.dto.notif;

import java.io.Serializable;

import lombok.Builder;

@Builder
public record MailBodyContentBuilder(String username, String confirmLink) implements Serializable {}




