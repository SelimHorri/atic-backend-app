package tn.cita.app.util;

import tn.cita.app.dto.notif.MailNotification;

public interface NotificationUtil {
	
	Boolean sendMail(final MailNotification mailNotification);
	Boolean sendSms();
	
}














