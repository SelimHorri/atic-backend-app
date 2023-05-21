package tn.cita.app.util;

import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import lombok.RequiredArgsConstructor;
import tn.cita.app.model.dto.notif.MailBodyContentBuilder;

@Component
@RequiredArgsConstructor
public class MailContentBuilder {
	
	private final SpringTemplateEngine templateEngine;
	
	public String build(final MailBodyContentBuilder mailBodyContentBuilder) {
		final Context context = new Context();
		context.setVariable("m", mailBodyContentBuilder);
		return this.templateEngine.process("confirm-register", context);
	}
	
}







