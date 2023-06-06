package tn.cita.app.model.dto.notif;

import java.io.Serializable;

public record SmsNotification(String to, String smsMsg) implements Serializable {}



