package tn.cita.app.resource;

import java.util.NoSuchElementException;
import java.util.Objects;

import jakarta.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import lombok.extern.slf4j.Slf4j;
import tn.cita.app.exception.payload.ExceptionMsg;
import tn.cita.app.exception.wrapper.ActuatorHealthException;
import tn.cita.app.exception.wrapper.BusinessException;
import tn.cita.app.model.dto.response.api.ApiResponse;

@ControllerAdvice
@Slf4j
public class ApiExceptionHandler {
	
	@ExceptionHandler(value = {
		BindException.class,
		MethodArgumentNotValidException.class,
		HttpMessageNotReadableException.class,
		ConstraintViolationException.class,
	})
	public <T extends BindException> ResponseEntity<ApiResponse<ExceptionMsg>> handleValidationException(final T e) {
		
		log.info("** Handle validation exception.. *\n");
		
		final var fieldError = Objects.requireNonNullElseGet(e.getBindingResult().getFieldError(), 
				() -> new FieldError(null, null, "Validation error happened, check again"));
		
		final var httpStatus = HttpStatus.BAD_REQUEST;
		final var exceptionMsg = new ExceptionMsg("*%s!**".formatted(fieldError.getDefaultMessage()));
		final var apiResponse = new ApiResponse<>(1, httpStatus, false, exceptionMsg);
		
		return ResponseEntity.status(httpStatus)
				.contentType(MediaType.APPLICATION_JSON)
				.body(apiResponse);
	}
	
	@ExceptionHandler(value = {
		BusinessException.class,
		NullPointerException.class,
		NoSuchElementException.class,
		BadCredentialsException.class,
		IllegalStateException.class,
		DisabledException.class,
		NumberFormatException.class,
		// UnauthorizedUserException.class, // already works for filter using resolver
		ActuatorHealthException.class,
	})
	public <T extends RuntimeException> ResponseEntity<ApiResponse<ExceptionMsg>> handleApiRequestException(final T e) {
		log.info("** Handle API request custom exception.. *\n");
		
		final var httpStatus = HttpStatus.BAD_REQUEST;
		final var exceptionMsg = new ExceptionMsg("#### %s! ####".formatted(e.getMessage()));
		final var apiResponse = new ApiResponse<>(1, httpStatus, false, exceptionMsg);
		
		return ResponseEntity.status(httpStatus)
				.contentType(MediaType.APPLICATION_JSON)
				.body(apiResponse);
	}
	
	@ExceptionHandler
	public ResponseEntity<ApiResponse<ExceptionMsg>> handleGeneralException(final Exception e) {
		log.info("** Handle API request custom exception.. *\n");
		
		final var httpStatus = HttpStatus.BAD_REQUEST;
		final var exceptionMsg = new ExceptionMsg("#### %s! ####".formatted(e.getMessage()));
		final var apiResponse = new ApiResponse<>(1, httpStatus, false, exceptionMsg);
		
		return ResponseEntity.status(httpStatus)
				.contentType(MediaType.APPLICATION_JSON)
				.body(apiResponse);
	}
	
}









