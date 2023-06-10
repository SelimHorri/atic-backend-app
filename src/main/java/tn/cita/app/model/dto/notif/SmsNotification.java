package tn.cita.app.model.dto.notif;

import lombok.Builder;

import java.io.Serializable;

@Builder
public record SmsNotification(String to, String smsMsg) implements Serializable {}





