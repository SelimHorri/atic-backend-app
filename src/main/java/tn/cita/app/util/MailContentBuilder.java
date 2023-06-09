package tn.cita.app.util;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Map;

@Component
@RequiredArgsConstructor
public class MailContentBuilder {
	
	private final SpringTemplateEngine templateEngine;
	
	public String build(final Map<String, Object> map) {
		final Context context = new Context();
		context.setVariable("map", map);
		return this.templateEngine.process("confirm-register", context);
	}
	
}




