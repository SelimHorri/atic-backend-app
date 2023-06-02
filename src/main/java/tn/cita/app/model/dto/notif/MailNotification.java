package tn.cita.app.model.dto.notif;

import java.io.Serializable;

import lombok.Builder;

public record MailNotification(String to, String subject, String body) implements Serializable {}



