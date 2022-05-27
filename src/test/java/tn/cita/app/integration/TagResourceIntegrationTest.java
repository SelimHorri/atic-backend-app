package tn.cita.app.integration;

import static org.hamcrest.CoreMatchers.endsWith;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.startsWith;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.reactive.server.WebTestClient;

import tn.cita.app.constant.AppConstant;
import tn.cita.app.container.AbstractSharedMySQLTestContainer;
import tn.cita.app.dto.TagDto;
import tn.cita.app.dto.response.api.ApiPayloadResponse;
import tn.cita.app.exception.payload.ExceptionMsg;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
class TagResourceIntegrationTest extends AbstractSharedMySQLTestContainer {
	
	@Autowired
	private WebTestClient webTestClient;
	
	@Test
	void givenValidPageOffset_whenFindAll_thenAllTagsBasedOnPageOffsetShouldBeReturned() {
		
		final var pageOffset = 1;
		
		final var list = List.of(
				TagDto.builder()
				.id(null)
				.name("barber")
				.build(),
				TagDto.builder()
				.id(null)
				.name("barber")
				.build(),
				TagDto.builder()
				.id(null)
				.name("barber")
				.build(),
				TagDto.builder()
				.id(null)
				.name("barber")
				.build(),
				TagDto.builder()
				.id(null)
				.name("barber")
				.build(),
				TagDto.builder()
				.id(null)
				.name("barber")
				.build(),
				TagDto.builder()
				.id(null)
				.name("barber")
				.build(),
				TagDto.builder()
				.id(null)
				.name("barber")
				.build(),
				TagDto.builder()
				.id(null)
				.name("barber")
				.build(),
				TagDto.builder()
				.id(null)
				.name("barber")
				.build(),
				TagDto.builder()
				.id(null)
				.name("manicure")
				.build()
		);
		
		final var expectedPayload = new ApiPayloadResponse<>(2, HttpStatus.OK, true, list);
		this.webTestClient
				.get()
				.uri(AppConstant.API_CONTEXT_V0 + "/tags/offset/{pageOffset}", pageOffset)
				.exchange()
				.expectStatus()
					.is2xxSuccessful()
				.expectBody()
					.jsonPath("$").value(notNullValue())
					.jsonPath("$.totalResult").value(is(expectedPayload.getTotalResult()))
					.jsonPath("$.acknowledge").value(is(expectedPayload.getAcknowledge()))
					.jsonPath("$.responseBody").value(notNullValue())
					.jsonPath("$.responseBody.size()").value(is(expectedPayload.getResponseBody().size()));
	}
	
	@Test
	void givenValidId_whenFindById_thenTagDtoShouldBeFound() {
		
		final int id = 1;
		final var tagDto = TagDto.builder()
				.id(id)
				.name("barber")
				.build();
		
		final var expectedPayload = new ApiPayloadResponse<>(1, HttpStatus.OK, true, tagDto);
		this.webTestClient
				.get()
				.uri(AppConstant.API_CONTEXT_V0 + "/tags/{id}", id)
				.exchange()
				.expectStatus()
					.is2xxSuccessful()
				.expectBody()
					.jsonPath("$").value(notNullValue())
					.jsonPath("$.totalResult").value(is(expectedPayload.getTotalResult()))
					.jsonPath("$.acknowledge").value(is(expectedPayload.getAcknowledge()))
					.jsonPath("$.responseBody").value(notNullValue())
					.jsonPath("$.responseBody.id").value(notNullValue())
					.jsonPath("$.responseBody.name").value(is(expectedPayload.getResponseBody().getName()));
	}
	
	@Test
	void givenInvalidId_whenFindById_thenTagNotFoundExceptionShouldBeThrown() {
		
		final int id = 0;
		final var expectedPayload = new ApiPayloadResponse<>(1, HttpStatus.BAD_REQUEST, false, 
				new ExceptionMsg(String.format("%sTag with id: %d not found%s", "#### ", id, "! ####")));
		
		this.webTestClient
				.get()
				.uri(AppConstant.API_CONTEXT_V0 + "/tags/{id}", id)
				.exchange()
				.expectStatus()
					.isBadRequest()
				.expectBody()
					.jsonPath("$").value(notNullValue())
					.jsonPath("$.totalResult").value(is(expectedPayload.getTotalResult()))
					.jsonPath("$.acknowledge").value(is(expectedPayload.getAcknowledge()))
					.jsonPath("$.responseBody").value(notNullValue())
					.jsonPath("$.responseBody.errorMsg").value(startsWith("#### "))
					.jsonPath("$.responseBody.errorMsg").value(endsWith("! ####"))
					.jsonPath("$.responseBody.errorMsg").value(is(expectedPayload.getResponseBody().getErrorMsg()));
	}
	
	
	
}














