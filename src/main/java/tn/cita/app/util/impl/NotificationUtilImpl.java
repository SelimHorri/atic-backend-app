package tn.cita.app.util.impl;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.dto.notif.MailNotification;
import tn.cita.app.exception.wrapper.MailNotificationNotProcessedException;
import tn.cita.app.util.MailContentBuilder;
import tn.cita.app.util.NotificationUtil;

@Component
@RequiredArgsConstructor
public class NotificationUtilImpl implements NotificationUtil {
	
	private final JavaMailSender javaMailSender;
	private final MailContentBuilder mailContentBuilder;
	
	@Async
	@Override
	public Boolean sendMail(final MailNotification mailNotification) {
		
		Boolean isSent = false;
		
		final MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
			final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
			mimeMessageHelper.setFrom(AppConstants.MAIL_SOURCE);
			mimeMessageHelper.setTo(mailNotification.getTo());
			mimeMessageHelper.setSubject(mailNotification.getSubject());
			mimeMessageHelper.setText(this.mailContentBuilder.build(mailNotification.getBody()), true);
		};
		
		try {
			this.javaMailSender.send(mimeMessagePreparator);
			isSent = true;
		}
		catch (MailException e) {
			e.printStackTrace();
			throw new MailNotificationNotProcessedException(String
					.format("Sending mail to %s not processed as expected", mailNotification.getTo()));
		}
		
		return isSent;
	}
	
	@Async
	@Override
	public Boolean sendSms() {
		return false;
	}
	
	
	
}














