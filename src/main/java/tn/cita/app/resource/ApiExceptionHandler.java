package tn.cita.app.resource;

import java.util.Optional;

import javax.validation.ConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.SignatureException;
import lombok.extern.slf4j.Slf4j;
import tn.cita.app.dto.response.api.ApiPayloadResponse;
import tn.cita.app.exception.payload.ExceptionMsg;
import tn.cita.app.exception.wrapper.AccessTokenExpiredException;
import tn.cita.app.exception.wrapper.CategoryNotFoundException;
import tn.cita.app.exception.wrapper.CredentialNotFoundException;
import tn.cita.app.exception.wrapper.CustomerNotFoundException;
import tn.cita.app.exception.wrapper.EmployeeNotFoundException;
import tn.cita.app.exception.wrapper.ExpiredVerificationTokenException;
import tn.cita.app.exception.wrapper.FavouriteNotFoundException;
import tn.cita.app.exception.wrapper.IllegalCredentialsException;
import tn.cita.app.exception.wrapper.IllegalRegistrationRoleTypeException;
import tn.cita.app.exception.wrapper.IllegalUserDetailsStateException;
import tn.cita.app.exception.wrapper.LocationNotFoundException;
import tn.cita.app.exception.wrapper.MailNotificationNotProcessedException;
import tn.cita.app.exception.wrapper.OrderedDetailNotFoundException;
import tn.cita.app.exception.wrapper.PasswordNotMatchException;
import tn.cita.app.exception.wrapper.RatingNotFoundException;
import tn.cita.app.exception.wrapper.ReservationNotFoundException;
import tn.cita.app.exception.wrapper.SaloonNotFoundException;
import tn.cita.app.exception.wrapper.SaloonTagNotFoundException;
import tn.cita.app.exception.wrapper.ServiceDetailNotFoundException;
import tn.cita.app.exception.wrapper.TagNotFoundException;
import tn.cita.app.exception.wrapper.UsernameAlreadyExistsException;
import tn.cita.app.exception.wrapper.VerificationTokenNotFoundException;

@RestControllerAdvice
@Slf4j
public class ApiExceptionHandler {
	
	@ExceptionHandler(value = {
		MethodArgumentNotValidException.class,
		HttpMessageNotReadableException.class,
		ConstraintViolationException.class,
	})
	public <T extends BindException> ResponseEntity<ApiPayloadResponse<ExceptionMsg>> handleValidationException(final T e, 
			final WebRequest webRequest) {
		
		log.info("**ApiExceptionHandler controller; ExceptionMsg; handle validation exception*\n");
		
		final var fieldError = Optional
				.ofNullable(e.getBindingResult().getFieldError())
				.orElseGet(() -> new FieldError(null, null, "Validation error happened, check again"));
		
		final var httpStatus = HttpStatus.BAD_REQUEST;
		final var exceptionMsg = ExceptionMsg.builder()
				.errorMsg(String.format("*%s!**", fieldError.getDefaultMessage()))
				.build();
		final var apiPayloadResponse = new ApiPayloadResponse<>(1, httpStatus, false, exceptionMsg);
		
		return ResponseEntity.status(httpStatus)
				.contentType(MediaType.APPLICATION_JSON)
				.body(apiPayloadResponse);
	}
	
	@ExceptionHandler(value = {
		BadCredentialsException.class,
		IllegalCredentialsException.class,
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
		IllegalRegistrationRoleTypeException.class,
		PasswordNotMatchException.class,
		MailNotificationNotProcessedException.class,
		ExpiredVerificationTokenException.class,
		DisabledException.class,
		IllegalUserDetailsStateException.class,
		UsernameAlreadyExistsException.class,
		AccessTokenExpiredException.class,
		SignatureException.class,
		ExpiredJwtException.class,
		NumberFormatException.class,
	})
	public <T extends RuntimeException> ResponseEntity<ApiPayloadResponse<ExceptionMsg>> handleApiRequestException(final T e, 
			final WebRequest webRequest) {
		log.info("**ApiExceptionHandler controller; ExceptionMsg; handle API request*\n");
		
		final var httpStatus = HttpStatus.BAD_REQUEST;
		final var exceptionMsg = ExceptionMsg.builder()
				.errorMsg(String.format("#### %s! ####", e.getMessage()))
				.build();
		final var apiPayloadResponse = new ApiPayloadResponse<>(1, httpStatus, false, exceptionMsg);
		
		return ResponseEntity.status(httpStatus)
				.contentType(MediaType.APPLICATION_JSON)
				.body(apiPayloadResponse);
	}
	
	
	
}














