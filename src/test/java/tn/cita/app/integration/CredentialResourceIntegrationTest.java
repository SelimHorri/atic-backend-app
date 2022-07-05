package tn.cita.app.integration;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

import java.util.UUID;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.reactive.server.WebTestClient;

import tn.cita.app.constant.AppConstant;
import tn.cita.app.container.AbstractSharedMySQLTestContainer;
import tn.cita.app.domain.UserRoleBasedAuthority;
import tn.cita.app.dto.CredentialDto;
import tn.cita.app.dto.response.api.ApiResponse;
import tn.cita.app.exception.payload.ExceptionMsg;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
@AutoConfigureMockMvc // required for UserDetailsService
@WithUserDetails(userDetailsServiceBeanName = "userDetailsServiceImpl", value = "selimhorri")
class CredentialResourceIntegrationTest extends AbstractSharedMySQLTestContainer {
	
	@Autowired
	private WebTestClient webTestClient;
	
	@Test
	void givenValidUsername_whenFindByUsername_thenCredentialDtoShouldBeFound() {
		
		final var username = "selimhorri";
		final var credentialDto = CredentialDto.builder()
				.id(null)
				.username(username)
				.userRoleBasedAuthority(UserRoleBasedAuthority.WORKER)
				.isEnabled(true)
				.isAccountNonExpired(true)
				.isAccountNonLocked(true)
				.isCredentialsNonExpired(true)
				.build();
		
		final var expectedPayload = new ApiResponse<>(1, HttpStatus.OK, true, credentialDto);
		
		this.webTestClient
				.get()
				.uri(AppConstant.API_CONTEXT_V0 + "/credentials/username/{username}", credentialDto.getUsername())
				.exchange()
				.expectStatus()
					.is2xxSuccessful()
				.expectBody()
					.jsonPath("$").value(notNullValue())
					.jsonPath("$.totalResult").value(is(expectedPayload.getTotalResult()))
					.jsonPath("$.acknowledge").value(is(expectedPayload.getAcknowledge()))
					.jsonPath("$.responseBody").value(notNullValue())
					.jsonPath("$.responseBody.username").value(is(expectedPayload.getResponseBody().getUsername()))
					.jsonPath("$.responseBody.role").value(is(expectedPayload.getResponseBody().getUserRoleBasedAuthority().name()))
					.jsonPath("$.responseBody.isEnabled").value(is(expectedPayload.getResponseBody().getIsEnabled()))
					.jsonPath("$.responseBody.isAccountNonExpired").value(is(expectedPayload.getResponseBody().getIsAccountNonExpired()))
					.jsonPath("$.responseBody.isAccountNonLocked").value(is(expectedPayload.getResponseBody().getIsAccountNonLocked()))
					.jsonPath("$.responseBody.isCredentialsNonExpired").value(is(expectedPayload.getResponseBody().getIsCredentialsNonExpired()));
	}
	
	@Test
	void givenInvalidUsername_whenFindByUsername_thenCredentialNotFoundExceptionShouldBeThrown() {
		
		final var username = "selimhorri" + UUID.randomUUID().toString();
		
		final var exceptionMsg = ExceptionMsg.builder()
				.errorMsg(String.format("#### Credential with username %s not found! ####", username))
				.build();
		
		final var expectedPayload = new ApiResponse<>(1, HttpStatus.BAD_REQUEST, false, exceptionMsg);
		
		this.webTestClient
				.get()
				.uri(AppConstant.API_CONTEXT_V0 + "/credentials/username/{username}", username)
				.exchange()
				.expectStatus()
					.isBadRequest()
				.expectBody()
					.jsonPath("$").value(notNullValue())
					.jsonPath("$.totalResult").value(is(expectedPayload.getTotalResult()))
					.jsonPath("$.acknowledge").value(is(expectedPayload.getAcknowledge()))
					.jsonPath("$.responseBody").value(notNullValue())
					.jsonPath("$.responseBody.errorMsg").value(is(expectedPayload.getResponseBody().getErrorMsg()));
					
	}
	
	
	
}













