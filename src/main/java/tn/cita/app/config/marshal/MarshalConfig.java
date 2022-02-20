package tn.cita.app.config.marshal;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.json.JsonMapper;

@Configuration
public class MarshalConfig {
	
	@Bean
	public ObjectMapper objectMapperBean() {
		return new JsonMapper()
				.enable(SerializationFeature.INDENT_OUTPUT);
	}
	
	
	
}










