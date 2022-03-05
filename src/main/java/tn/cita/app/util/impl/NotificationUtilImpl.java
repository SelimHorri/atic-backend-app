package tn.cita.app.util.impl;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import tn.cita.app.dto.notif.MailNotification;
import tn.cita.app.exception.wrapper.MailNotificationNotProcessedException;
import tn.cita.app.util.NotificationUtil;

@Component
@RequiredArgsConstructor
public class NotificationUtilImpl implements NotificationUtil {
	
	private final JavaMailSender javaMailSender;
	
	@Async
	@Override
	public boolean sendMail(final MailNotification mailNotification) {
		
		boolean isSent = false;
		
		final MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
			final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
			mimeMessageHelper.setFrom(mailNotification.getFrom());
			mimeMessageHelper.setTo(mailNotification.getTo());
			mimeMessageHelper.setSubject(mailNotification.getSubject());
			mimeMessageHelper.setText(mailNotification.getBody());
		};
		
		try {
			this.javaMailSender.send(mimeMessagePreparator);
			isSent = true;
		}
		catch (MailException e) {
			e.printStackTrace();
			throw new MailNotificationNotProcessedException(String
					.format("Mail to %s not processed as expected", mailNotification.getTo()));
		}
		
		return isSent;
	}
	
	@Async
	@Override
	public boolean sendSms() {
		return false;
	}
	
	
	
}














