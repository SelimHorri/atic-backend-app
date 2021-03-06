package tn.cita.app.integration;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.CoreMatchers.containsStringIgnoringCase;
import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.startsWith;

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
import tn.cita.app.container.AbstractSharedMySQLTestContainer;
import tn.cita.app.dto.request.LoginRequest;
import tn.cita.app.dto.response.LoginResponse;
import tn.cita.app.dto.response.api.ApiPayloadResponse;
import tn.cita.app.exception.payload.ExceptionMsg;
import tn.cita.app.service.AuthenticationService;
import tn.cita.app.util.JwtUtil;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class AuthenticationResourceIntegrationTest extends AbstractSharedMySQLTestContainer {
	
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
	void givenLoginApiUrl_whenRequestIsValid_thenLoginResponseShouldBeReturned() {
		
		this.loginResponse = this.authenticationService.authenticate(loginRequest);
		final var apiPayloadResponse = new ApiPayloadResponse<>(1, HttpStatus.OK, true, loginResponse);
		
		this.webTestClient
				.post()
				.uri(AppConstant.API_CONTEXT_V0 + "/authenticate")
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
					.jsonPath("$.totalResult").value(is(apiPayloadResponse.getTotalResult()))
					.jsonPath("$.httpStatus").value(is(apiPayloadResponse.getHttpStatus().name()))
					.jsonPath("$.acknowledge").value(is(apiPayloadResponse.getAcknowledge()))
					.jsonPath("$.responseBody").value(notNullValue())
					.jsonPath("$.responseBody.username").value(is(apiPayloadResponse.getResponseBody().getUsername()));
		
		final boolean validateToken = this.jwtUtil.validateToken(this.loginResponse.getJwtToken(), 
				this.userDetailsService.loadUserByUsername(this.loginResponse.getUsername()));
		assertThat(validateToken).isTrue();
	}
	
	@Test
	void givenLoginApiUrl_whenRequestWithInvalidUsername_thenExceptionMsgShouldBeReturned() {
		
		final var wrongUsernameLoginRequest = new LoginRequest(this.loginRequest + "iiii", this.loginRequest.getPassword());
		final var apiPayloadResponse = new ApiPayloadResponse<ExceptionMsg>(1, HttpStatus.BAD_REQUEST, false, 
				new ExceptionMsg("#### Credential with username " + wrongUsernameLoginRequest.getUsername() + " not found! ####"));
		
		this.webTestClient
				.post()
				.uri(AppConstant.API_CONTEXT_V0 + "/authenticate")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(wrongUsernameLoginRequest)
				.exchange()
				.expectStatus()
					.isBadRequest()
				.expectHeader()
					.contentType(MediaType.APPLICATION_JSON)
				.expectBody()
					.jsonPath("$").value(notNullValue())
					.jsonPath("$.totalResult").value(is(apiPayloadResponse.getTotalResult()))
					.jsonPath("$.httpStatus").value(is(apiPayloadResponse.getHttpStatus().name()))
					.jsonPath("$.acknowledge").value(is(apiPayloadResponse.getAcknowledge()))
					.jsonPath("$.responseBody").value(notNullValue())
					.jsonPath("$.responseBody.errorMsg").value(startsWith("#### "))
					.jsonPath("$.responseBody.errorMsg").value(endsWith("! ####"))
					.jsonPath("$.responseBody.errorMsg")
						.value(containsStringIgnoringCase("Credential with username " + wrongUsernameLoginRequest.getUsername() + " not found"))
					.jsonPath("$.responseBody.errorMsg").value(is(apiPayloadResponse.getResponseBody().getErrorMsg()));
	}
	
	@Test
	void givenLoginApiUrl_whenRequestWithInvalidPasswordOrElse_thenExceptionMsgShouldBeReturned() {
		
		final var wrongCredentialsLoginRequest = new LoginRequest(this.loginRequest.getUsername(), this.loginRequest.getPassword() + "012545");
		final var apiPayloadResponse = new ApiPayloadResponse<ExceptionMsg>(1, HttpStatus.BAD_REQUEST, false, 
				new ExceptionMsg("#### Bad credentials! ####"));
		
		this.webTestClient
				.post()
				.uri(AppConstant.API_CONTEXT_V0 + "/authenticate")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(wrongCredentialsLoginRequest)
				.exchange()
				.expectStatus()
					.isBadRequest()
				.expectHeader()
					.contentType(MediaType.APPLICATION_JSON)
				.expectBody()
					.jsonPath("$").value(notNullValue())
					.jsonPath("$.totalResult").value(is(apiPayloadResponse.getTotalResult()))
					.jsonPath("$.httpStatus").value(is(apiPayloadResponse.getHttpStatus().name()))
					.jsonPath("$.acknowledge").value(is(apiPayloadResponse.getAcknowledge()))
					.jsonPath("$.responseBody").value(notNullValue())
					.jsonPath("$.responseBody.errorMsg").value(startsWith("#### "))
					.jsonPath("$.responseBody.errorMsg").value(endsWith("! ####"))
					.jsonPath("$.responseBody.errorMsg").value(containsStringIgnoringCase("Bad credentials"))
					.jsonPath("$.responseBody.errorMsg").value(is(apiPayloadResponse.getResponseBody().getErrorMsg()));
	}
	
	@Test
	void givenLoginApiUrl_whenRequestUsernameIsBlank_thenCustomValidationExceptionMsgShouldBeReturned() {
		
		final var wrongCredentialsLoginRequest = new LoginRequest(null, this.loginRequest.getPassword());
		final var apiPayloadResponse = new ApiPayloadResponse<ExceptionMsg>(1, HttpStatus.BAD_REQUEST, false, 
				new ExceptionMsg("*Input username should not be blank!**"));
		
		this.webTestClient
				.post()
				.uri(AppConstant.API_CONTEXT_V0 + "/authenticate")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(wrongCredentialsLoginRequest)
				.exchange()
				.expectStatus()
					.isBadRequest()
				.expectHeader()
					.contentType(MediaType.APPLICATION_JSON)
				.expectBody()
					.jsonPath("$").value(notNullValue())
					.jsonPath("$.totalResult").value(is(apiPayloadResponse.getTotalResult()))
					.jsonPath("$.httpStatus").value(is(apiPayloadResponse.getHttpStatus().name()))
					.jsonPath("$.acknowledge").value(is(apiPayloadResponse.getAcknowledge()))
					.jsonPath("$.responseBody").value(notNullValue())
					.jsonPath("$.responseBody.errorMsg").value(startsWith("*"))
					.jsonPath("$.responseBody.errorMsg").value(endsWith("!**"))
					.jsonPath("$.responseBody.errorMsg").value(containsStringIgnoringCase("Input username should not be blank"))
					.jsonPath("$.responseBody.errorMsg").value(is(apiPayloadResponse.getResponseBody().getErrorMsg()));
	}
	
	@Test
	void givenLoginApiUrl_whenRequestPasswordIsEmpty_thenCustomValidationExceptionMsgShouldBeReturned() {
		
		final var wrongCredentialsLoginRequest = new LoginRequest(this.loginRequest.getUsername(), null);
		final var apiPayloadResponse = new ApiPayloadResponse<ExceptionMsg>(1, HttpStatus.BAD_REQUEST, false, 
				new ExceptionMsg("*Input password should not be blank!**"));
		
		this.webTestClient
				.post()
				.uri(AppConstant.API_CONTEXT_V0 + "/authenticate")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.bodyValue(wrongCredentialsLoginRequest)
				.exchange()
				.expectStatus()
					.isBadRequest()
				.expectHeader()
					.contentType(MediaType.APPLICATION_JSON)
				.expectBody()
					.jsonPath("$").value(notNullValue())
					.jsonPath("$.totalResult").value(is(apiPayloadResponse.getTotalResult()))
					.jsonPath("$.httpStatus").value(is(apiPayloadResponse.getHttpStatus().name()))
					.jsonPath("$.acknowledge").value(is(apiPayloadResponse.getAcknowledge()))
					.jsonPath("$.responseBody").value(notNullValue())
					.jsonPath("$.responseBody.errorMsg").value(startsWith("*"))
					.jsonPath("$.responseBody.errorMsg").value(endsWith("!**"))
					.jsonPath("$.responseBody.errorMsg").value(containsStringIgnoringCase("Input password should not be blank"))
					.jsonPath("$.responseBody.errorMsg").value(is(apiPayloadResponse.getResponseBody().getErrorMsg()));
	}
	
	
	
}













