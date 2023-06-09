package tn.cita.app.integration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;
import tn.cita.app.constant.AppConstants;
import tn.cita.app.container.AbstractSharedMySQLTestContainer;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class CategoryResourceIntegrationTest extends AbstractSharedMySQLTestContainer {
	
	@Autowired
	private WebTestClient webTestClient;
	
	@Test
	void givenSaloonId_whenFindAllCategoriesBySaloonId_thenPageOfCategoryDtosShouldBeReturned() {
		
		final var saloonId = 2;
		
		this.webTestClient
				.get()
				.uri(AppConstants.API_CONTEXT_V0 + "/categories/{saloonId}", saloonId)
				.exchange()
				.expectStatus()
					.is2xxSuccessful()
				.expectBody()
				.jsonPath("$").value(notNullValue())
				.jsonPath("$.httpStatus").value(is(HttpStatus.OK.name()))
				.jsonPath("$.acknowledge").value(is(true))
				.jsonPath("$.responseBody").value(notNullValue());
		
	}
	
}







