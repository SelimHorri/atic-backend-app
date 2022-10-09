package tn.cita.app.dto.notif;

import java.io.Serializable;

import lombok.Builder;

@Builder
public record MailNotification(String to, String subject, MailBodyContentBuilder body) implements Serializable {}




