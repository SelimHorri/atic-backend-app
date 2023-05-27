package tn.cita.app.util;

import tn.cita.app.model.dto.notif.MailNotification;

public interface NotificationUtil {
	
	void sendMail(final MailNotification mailNotification);
	void sendSms();
	
}





