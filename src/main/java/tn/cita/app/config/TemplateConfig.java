package tn.cita.app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class TemplateConfig {
	
	@Bean
	RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	
	
}












