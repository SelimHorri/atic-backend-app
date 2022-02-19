package tn.cita.app.dto.response;

import java.io.Serializable;
import java.time.Instant;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.InstantDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.InstantSerializer;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import tn.cita.app.constant.AppConstant;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
@ToString
public final class ApiResponse<T> implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	@JsonFormat(pattern = AppConstant.INSTANT_FORMAT)
	@JsonSerialize(using = InstantSerializer.class)
	@JsonDeserialize(using = InstantDeserializer.class)
	private transient final Instant timestamp = Instant.now();
	private final Integer totalResult;
	private final HttpStatus httpStatus;
	private final Boolean acknowledge;
	private final T responseBody;
	
}












