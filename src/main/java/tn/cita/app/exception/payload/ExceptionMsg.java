package tn.cita.app.exception.payload;

import java.io.Serializable;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@AllArgsConstructor
@Data
@Builder
public final class ExceptionMsg implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonInclude(value = Include.NON_NULL)
	private Throwable throwable;
	private final Boolean acknowledge;
	private final HttpStatus httpStatus;
	private final String errorMsg;
	
}











