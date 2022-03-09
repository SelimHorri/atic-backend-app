package tn.cita.app.resource.v0;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.reactive.server.WebTestClient;

import tn.cita.app.service.RegistrationService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class RegistrationResourceTest {
	
	@Autowired
	private WebTestClient webTestClient;
	
	@MockBean
	private RegistrationService registrationService;
	
	
	
}













