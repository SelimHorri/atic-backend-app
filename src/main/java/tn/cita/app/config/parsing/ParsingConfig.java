package tn.cita.app.config.parsing;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParsingConfig {
	
	@Bean
	ObjectMapper objectMapper() {
		return new JsonMapper()
				.enable(SerializationFeature.INDENT_OUTPUT);
	}
	
}



