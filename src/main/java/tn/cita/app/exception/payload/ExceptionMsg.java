package tn.cita.app.exception.payload;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@AllArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
@Builder
public final class ExceptionMsg implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonInclude(value = Include.NON_NULL)
	private Throwable throwable;
	private final String errorMsg;
	
}











