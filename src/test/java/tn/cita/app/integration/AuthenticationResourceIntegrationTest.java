package tn.cita.app.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.test.web.reactive.server.WebTestClient;

import tn.cita.app.constant.AppConstant;
import tn.cita.app.container.AbstractTestSharedMySQLContainer;
import tn.cita.app.dto.request.LoginRequest;
import tn.cita.app.dto.response.LoginResponse;
import tn.cita.app.dto.response.api.AuthenticationLoginApiResponse;
import tn.cita.app.service.AuthenticationService;
import tn.cita.app.util.JwtUtil;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class AuthenticationResourceIntegrationTest extends AbstractTestSharedMySQLContainer {
	
	@Autowired
	private AuthenticationService authenticationService;
	
	@Autowired
	private JwtUtil jwtUtil;
	
	@Autowired
	private UserDetailsService userDetailsService;
	
	@Autowired
	private WebTestClient webTestClient;
	private LoginRequest loginRequest;
	private LoginResponse loginResponse;
	
	@BeforeEach
	void setUp() {
		loginRequest = new LoginRequest("selimhorri", "0000");
	}
	
	@Test
	void givenLoginApiUrl_whenRequestIsValid_thenLoginShouldLoginResponseShouldBeReturned() {
		
		this.loginResponse = this.authenticationService.login(loginRequest);
		final var apiResponse = new AuthenticationLoginApiResponse(1, HttpStatus.OK, true, loginResponse);
		
		this.webTestClient
				.post()
				.uri(AppConstant.API_CONTEXT_V0 + "/authentication/login")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(loginRequest)
				.exchange()
				.expectStatus()
					.is2xxSuccessful()
				.expectHeader()
					.contentType(MediaType.APPLICATION_JSON)
				.expectBody()
					.jsonPath("$").value(notNullValue())
					.jsonPath("$.totalResult").value(is(apiResponse.getTotalResult()))
					.jsonPath("$.httpStatus").value(is(apiResponse.getHttpStatus().name()))
					.jsonPath("$.acknowledge").value(is(apiResponse.getAcknowledge()))
					.jsonPath("$.loginResponse").value(notNullValue())
					.jsonPath("$.loginResponse.username").value(is(apiResponse.getLoginResponse().getUsername()));
		
		final boolean validateToken = this.jwtUtil.validateToken(this.loginResponse.getJwtToken(), 
				this.userDetailsService.loadUserByUsername(this.loginResponse.getUsername()));
		assertThat(validateToken).isTrue();
	}
	
	
	
}













