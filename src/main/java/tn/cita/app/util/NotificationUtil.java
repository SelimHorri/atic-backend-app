package tn.cita.app.util;

import tn.cita.app.dto.notif.MailNotification;

public interface NotificationUtil {
	
	boolean sendMail(final MailNotification mailNotification);
	boolean sendSms();
	
}














