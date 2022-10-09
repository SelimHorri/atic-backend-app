package tn.cita.app.resource.v0;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;

import tn.cita.app.constant.AppConstants;
import tn.cita.app.dto.request.LoginRequest;
import tn.cita.app.dto.response.LoginResponse;
import tn.cita.app.dto.response.api.ApiResponse;
import tn.cita.app.service.v0.AuthenticationService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class AuthenticationResourceTest {
	
	@MockBean
	private AuthenticationService authenticationService;
	
	@Autowired
	private WebTestClient webTestClient;
	private LoginRequest loginRequest;
	private LoginResponse loginResponse;
	
	@BeforeEach
	void setUp() throws Exception {
		
		loginRequest = new LoginRequest("selimhorri", "0000");
		loginResponse = new LoginResponse("selimhorri", "userJwtToken");
		
		when(this.authenticationService.authenticate(loginRequest))
				.thenReturn(loginResponse);
	}
	
	@Test
	void givenLoginApiUrl_whenRequestIsValid_thenLoginResponseShouldBeReturned() {
		
		final var apiPayloadResponse = new ApiResponse<>(1, HttpStatus.OK, true, this.loginResponse);
		this.webTestClient
				.post()
				.uri(AppConstants.API_CONTEXT_V0 + "/authenticate")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(loginRequest)
				.exchange()
				.expectStatus()
					.is2xxSuccessful()
				.expectHeader()
					.contentType(MediaType.APPLICATION_JSON_VALUE)
				.expectBody()
					.jsonPath("$").value(notNullValue())
					.jsonPath("$.totalResult").value(is(apiPayloadResponse.getTotalResult()))
					.jsonPath("$.httpStatus").value(is(apiPayloadResponse.getHttpStatus().name()))
					.jsonPath("$.acknowledge").value(is(apiPayloadResponse.getAcknowledge()))
					.jsonPath("$.responseBody").value(notNullValue())
					.jsonPath("$.responseBody.username").value(is(apiPayloadResponse.getResponseBody().username()))
					.jsonPath("$.responseBody.jwtToken").value(is(apiPayloadResponse.getResponseBody().jwtToken()));
	}
	
	
	
}














