package tn.cita.app.exception;

import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;
import tn.cita.app.dto.response.ApiResponse;
import tn.cita.app.exception.payload.ExceptionMsg;
import tn.cita.app.exception.wrapper.CategoryNotFoundException;
import tn.cita.app.exception.wrapper.CredentialNotFoundException;
import tn.cita.app.exception.wrapper.CustomerNotFoundException;
import tn.cita.app.exception.wrapper.EmployeeNotFoundException;
import tn.cita.app.exception.wrapper.FavouriteNotFoundException;
import tn.cita.app.exception.wrapper.IllegalCredentialsException;
import tn.cita.app.exception.wrapper.LocationNotFoundException;
import tn.cita.app.exception.wrapper.OrderedDetailNotFoundException;
import tn.cita.app.exception.wrapper.RatingNotFoundException;
import tn.cita.app.exception.wrapper.ReservationNotFoundException;
import tn.cita.app.exception.wrapper.SaloonNotFoundException;
import tn.cita.app.exception.wrapper.SaloonTagNotFoundException;
import tn.cita.app.exception.wrapper.ServiceDetailNotFoundException;
import tn.cita.app.exception.wrapper.TagNotFoundException;
import tn.cita.app.exception.wrapper.VerificationTokenNotFoundException;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {
	
	@ExceptionHandler(value = {
		MethodArgumentNotValidException.class,
		HttpMessageNotReadableException.class,
	})
	public <T extends BindException> ResponseEntity<ApiResponse<ExceptionMsg>> handleValidationException(final T e) {
		
		log.info("**ApiExceptionHandler controller, handle validation exception*\n");
		
		final var fieldError = Optional
				.ofNullable(e.getBindingResult().getFieldError())
				.orElseGet(() -> new FieldError(null, null, "Validation error happened, check again"));
		
		final var httpStatus = HttpStatus.BAD_REQUEST;
		final var exceptionMsg = ExceptionMsg.builder()
				.errorMsg("*" + fieldError.getDefaultMessage() + "!**")
				.httpStatus(httpStatus)
				.build();
		final var apiResponse = new ApiResponse<>(1, httpStatus, false, exceptionMsg);
		
		return ResponseEntity.status(httpStatus)
				.contentType(MediaType.APPLICATION_JSON)
				.body(apiResponse);
	}
	
	@ExceptionHandler(value = {
		IllegalStateException.class,
		CategoryNotFoundException.class,
		CredentialNotFoundException.class,
		CustomerNotFoundException.class,
		EmployeeNotFoundException.class,
		FavouriteNotFoundException.class,
		LocationNotFoundException.class,
		OrderedDetailNotFoundException.class,
		RatingNotFoundException.class,
		ReservationNotFoundException.class,
		SaloonNotFoundException.class,
		SaloonTagNotFoundException.class,
		ServiceDetailNotFoundException.class,
		TagNotFoundException.class,
		VerificationTokenNotFoundException.class,
		IllegalCredentialsException.class,
	})
	public <T extends RuntimeException> ResponseEntity<ApiResponse<ExceptionMsg>> handleApiRequestException(final T e) {
		
		log.info("**ApiExceptionHandler controller, handle API request*\n");
		
		final var httpStatus = HttpStatus.BAD_REQUEST;
		final var exceptionMsg = ExceptionMsg.builder()
				.errorMsg("#### " + e.getMessage() + "! ####")
				.httpStatus(httpStatus)
				.build();
		final var apiResponse = new ApiResponse<>(1, httpStatus, false, exceptionMsg);
		
		return ResponseEntity.status(httpStatus)
				.contentType(MediaType.APPLICATION_JSON)
				.body(apiResponse);
	}
	
	
	
}












