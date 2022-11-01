package tn.cita.app.model.dto.notif;

import java.io.Serializable;

import lombok.Builder;

@Builder
public record SmsNotification(String to, String smsMsg) implements Serializable {}





