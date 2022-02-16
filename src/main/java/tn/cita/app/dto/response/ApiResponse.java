package tn.cita.app.dto.response;

import java.io.Serializable;
import java.time.ZoneId;
import java.time.ZonedDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.ser.ZonedDateTimeSerializer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public final class ApiResponse<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = "dd-MM-yyyy__HH:mm:ss:SSSSSS")
	@JsonSerialize(using = ZonedDateTimeSerializer.class)
	private final ZonedDateTime timestamp = ZonedDateTime.now(ZoneId.systemDefault());
	private final Integer totalResult;
	private final HttpStatus httpStatus;
	private final Boolean acknowledge;
	private final T responseBody;
	
}












