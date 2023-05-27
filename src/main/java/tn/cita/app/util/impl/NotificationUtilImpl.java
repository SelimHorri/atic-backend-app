package tn.cita.app.util.impl;

import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.exception.wrapper.MailNotificationNotProcessedException;
import tn.cita.app.model.dto.notif.MailNotification;
import tn.cita.app.util.MailContentBuilder;
import tn.cita.app.util.NotificationUtil;

@Component
@RequiredArgsConstructor
public class NotificationUtilImpl implements NotificationUtil {
	
	private final JavaMailSender javaMailSender;
	private final MailContentBuilder mailContentBuilder;
	
	@Async
	@Override
	public void sendMail(final MailNotification mailNotification) {
		
		final MimeMessagePreparator mimeMessagePreparator = mimeMessage -> {
			final MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
			mimeMessageHelper.setFrom(AppConstants.MAIL_SOURCE);
			mimeMessageHelper.setTo(mailNotification.to());
			mimeMessageHelper.setSubject(mailNotification.subject());
			mimeMessageHelper.setText(this.mailContentBuilder.build(mailNotification.body()), true);
		};
		
		try {
			this.javaMailSender.send(mimeMessagePreparator);
		}
		catch (MailException e) {
			throw new MailNotificationNotProcessedException("Sending mail to %s not processed as expected"
					.formatted(mailNotification.to()));
		}
	}
	
	@Async
	@Override
	public void sendSms() {
		
	}
	
}






