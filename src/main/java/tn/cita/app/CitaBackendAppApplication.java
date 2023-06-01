package tn.cita.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableAsync
@EnableScheduling
@EnableCaching
public class CitaBackendAppApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(CitaBackendAppApplication.class, args);
	}
	
}



