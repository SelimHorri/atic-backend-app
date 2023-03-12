package tn.cita.app.util;

import tn.cita.app.model.dto.notif.MailNotification;

public interface NotificationUtil {
	
	Boolean sendMail(final MailNotification mailNotification);
	Boolean sendSms();
	
}





